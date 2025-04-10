package com.ongi.ongi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.auth.SignInResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.provider.JwtProvider;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuhtServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super ResponseDto> signIn(SignInRequestDto dto) {
        
        String accessToken = null;

        try {
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.authFail();

            String userPassword = dto.getUserPassword();
            String savedPassword = userEntity.getUserPassword();

            if (!userPassword.equals(savedPassword)) return ResponseDto.authFail();

            accessToken = jwtProvider.create(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return SignInResponseDto.success(accessToken);
    }


    @Override
    public ResponseEntity<ResponseDto> signUp(ResponseDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signUp'");
    }
    
}
