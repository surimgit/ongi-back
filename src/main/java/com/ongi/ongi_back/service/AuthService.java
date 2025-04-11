package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface  AuthService {
    ResponseEntity<ResponseDto> signUp(ResponseDto dto);
    ResponseEntity<? super ResponseDto> signIn(SignInRequestDto dto);
}
