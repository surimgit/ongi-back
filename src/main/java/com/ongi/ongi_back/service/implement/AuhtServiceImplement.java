package com.ongi.ongi_back.service.implement;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ongi.ongi_back.common.dto.request.auth.IdCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.ResignedCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignUpRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.auth.SignInResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.util.RedisUtil;
import com.ongi.ongi_back.provider.JwtProvider;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@Slf4j
public class AuhtServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String smsConfirmNum = createSmsKey();
    private final RedisUtil redisUtil;

    @Value("${solapi.apiKey}")
    private String accessKey;
    @Value("${solapi.apiSecret}")
    private String secretKey;
    @Value("${solapi.sender}")
    private String senderTelNumber;

    public ResponseEntity<? super ResponseDto> solapiSendSms(String to, String text){
        try{
            String timeStamp = Long.toString(System.currentTimeMillis());
            String salt = UUID.randomUUID().toString();

            String signature = getSignature(timeStamp, salt, secretKey);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "HMAC-SHA256 apikey="+ accessKey +
                        ", date=" + timeStamp + ", salt=" + salt + ", signature=" + signature);
            Map<String, Object> body = new HashMap<>();
            body.put("to", to);
            body.put("from", senderTelNumber);
            body.put("text", text);
            body.put("type", "SMS");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            ResponseEntity<String> response = restTemplate.postForEntity("https://api.solapi.com/messages/v4/send", request, String.class);

            if(response.getStatusCode() == HttpStatus.OK){
                return ResponseDto.success(HttpStatus.OK);
            } else {
                return ResponseDto.databaseError();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    public String getSignature(String timestamp, String salt, String secretKey) throws Exception {
        String message = timestamp + salt;
    
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
    
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        return Base64.encodeBase64String(rawHmac);
    }


    public static String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    @Override
    public ResponseEntity<? super ResponseDto> sendVerificationCode(String telNumber) {
        String code = createSmsKey();
        String message = "[서비스명] 인증번호 [" + code + "] 를 입력해주세요.";

        return solapiSendSms(telNumber, message);
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        try {
            
            String userId = dto.getUserId();
            boolean existUser = userRepository.existsByUserId(userId);
            if(existUser) return ResponseDto.existUser();

            String userPassword = dto.getUserPassword();
            String encodedPassword = passwordEncoder.encode(userPassword);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean existUser = userRepository.existsByUserId(userId);
            if(existUser) return ResponseDto.existUser();
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super ResponseDto> signIn(SignInRequestDto dto) {
        
        String accessToken = null;

        try {
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.authFail();

            String userPassword = dto.getUserPassword();
            String encodedPassword = userEntity.getUserPassword();
            boolean isMatch = passwordEncoder.matches(userPassword, encodedPassword);
            if (!isMatch) return ResponseDto.authFail();
      

            accessToken = jwtProvider.create(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return SignInResponseDto.success(accessToken);
    }


    @Override
    public ResponseEntity<ResponseDto> resignedCheck(ResignedCheckRequestDto dto) {
        try {
            String telNumber = dto.getTelNumber();
            UserEntity userEntity = userRepository.findByTelNumber(telNumber);
            if(userEntity == null || userEntity.getIsResigned()) return ResponseDto.authFail();
            
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }


    
    
}
