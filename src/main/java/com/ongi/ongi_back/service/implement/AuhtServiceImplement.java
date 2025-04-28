package com.ongi.ongi_back.service.implement;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.auth.FindIdRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.FindPasswordRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.IdCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.ResignedCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignUpRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.auth.FindIdResponseDto;
import com.ongi.ongi_back.common.dto.response.auth.SignInResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.entity.VerificationCodeEntity;
import com.ongi.ongi_back.common.util.RedisUtil;
import com.ongi.ongi_back.provider.JwtProvider;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.repository.VerificationCodeRepository;
import com.ongi.ongi_back.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class AuhtServiceImplement implements AuthService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DefaultMessageService messageService;
    private final String senderTelNumber;

    public AuhtServiceImplement(
        UserRepository userRepository,
        JwtProvider jwtProvider,
        RedisUtil redisUtil,
        @Value("${solapi.apiKey}") String apiKey,
        @Value("${solapi.apiSecret}") String apiSecret,
        @Value("${solapi.sender}") String senderTelNumber
    , VerificationCodeRepository verificationCodeRepository) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.senderTelNumber = senderTelNumber;
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.solapi.com");
        this.verificationCodeRepository = verificationCodeRepository;
    }

    public static String createSmsKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            key.append(rnd.nextInt(10));
        }
        return key.toString();
    }

    public ResponseEntity<? super ResponseDto> solapiSendSms(String to, String text) {
        try {
            Message message = new Message();
            message.setFrom(senderTelNumber);
            message.setTo(to);
            message.setText(text);

            SingleMessageSendingRequest request = new SingleMessageSendingRequest(message);

            SingleMessageSentResponse response = messageService.sendOne(request);
            log.info("SMS 전송 성공: {}", response.getMessageId());

            return ResponseDto.success(HttpStatus.OK);
        } catch (Exception e) {
            log.error("SMS 발송 중 예외 발생: ", e);
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super ResponseDto> sendVerificationCode(String telNumber) {
        String code = createSmsKey();

        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(3);

        VerificationCodeEntity verificationCodeEntity = new VerificationCodeEntity();
        verificationCodeEntity.setTelNumber(telNumber);
        verificationCodeEntity.setCode(code);
        verificationCodeEntity.setExpiryTime(expiryTime);
        verificationCodeRepository.save(verificationCodeEntity);

        String message = "[온기] 인증번호 [" + code + "] 를 입력해주세요.";
        return solapiSendSms(telNumber, message);
    }

    @Override
    public boolean validateVerificationCode(String telNumber, String code) {
        Optional<VerificationCodeEntity> verificationCodeOptional = verificationCodeRepository.findByTelNumberAndCode(telNumber, code);

        if (verificationCodeOptional.isPresent()) {
            VerificationCodeEntity verificationCode = verificationCodeOptional.get();

            if (LocalDateTime.now().isBefore(verificationCode.getExpiryTime())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Scheduled(fixedRate=3600000) //1시간마다  실행
    @Transactional
    public void deleteExpiredVerificationCodes(){
        LocalDateTime now = LocalDateTime.now();
        verificationCodeRepository.deleteAllByExpiryTimeBefore(now);
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean existUser = userRepository.existsByUserId(userId);
            if (existUser) return ResponseDto.existUser();

            String encodedPassword = passwordEncoder.encode(dto.getUserPassword());
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            String nickname = userEntity.getNickname();
            userEntity.setNickname(nickname);
            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            boolean existUser = userRepository.existsByUserId(dto.getUserId());
            if (existUser) return ResponseDto.existUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super ResponseDto> signIn(SignInRequestDto dto) {
        try {
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.authFail();

            boolean isMatch = passwordEncoder.matches(dto.getUserPassword(), userEntity.getUserPassword());

            if (!isMatch) return ResponseDto.authFail();

            String accessToken = jwtProvider.create(userId);
            return SignInResponseDto.success(accessToken);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> resignedCheck(ResignedCheckRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByTelNumber(dto.getTelNumber());
            if (userEntity == null) return ResponseDto.authFail();
            if (userEntity.getIsResigned()) return ResponseDto.resignedUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override

    public ResponseEntity<? super ResponseDto> findId(FindIdRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByNicknameAndTelNumber(dto.getNickname(), dto.getTelNumber());
            if(userEntity == null) return ResponseDto.noExistUser();

            return FindIdResponseDto.success(userEntity.getUserId());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super ResponseDto> findPassword(FindPasswordRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByUserIdAndTelNumber(dto.getUserId(), dto.getTelNumber());
            if(userEntity == null) return ResponseDto.noExistUser();

            String tempPassword = createTempPassword();
            String encoded = passwordEncoder.encode(tempPassword);
            userEntity.setUserPassword(encoded);
            userRepository.save(userEntity);

            String message = "[온기] 임시 비밀번호는 [" + tempPassword + "] 입니다.";
            return solapiSendSms(dto.getTelNumber(), message);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    public String createTempPassword() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}

