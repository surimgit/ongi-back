package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.auth.IdCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.ResignedCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignUpRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface  AuthService {
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<ResponseDto> resignedCheck(ResignedCheckRequestDto dto);
    ResponseEntity<? super ResponseDto> sendVerificationCode(String telNumber);
    ResponseEntity<? super ResponseDto> signIn(SignInRequestDto dto);
}
