package com.ongi.ongi_back.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.auth.FindIdRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.FindPasswordRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.IdCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.ResignedCheckRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignInRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.SignUpRequestDto;
import com.ongi.ongi_back.common.dto.request.auth.VerificationRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.auth.SignInResponseDto;
import com.ongi.ongi_back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthContoller {
    
    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/send-verify-code")
    public ResponseEntity<? super ResponseDto> sendVerificationCode(
        @RequestBody Map<String, String> request
    ){
        String telNumber = request.get("telNumber");
        return authService.sendVerificationCode(telNumber);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Boolean> validateVerificationCode(
        @RequestBody VerificationRequestDto requestBody
    ){
        boolean isValid = authService.validateVerificationCode(requestBody.getTelNumber(),requestBody.getCode());
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/resigned-check")
    public ResponseEntity<ResponseDto> resignedCheck(
        @RequestBody @Valid ResignedCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.resignedCheck(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/find-id")
    public ResponseEntity<? super ResponseDto> findId(
        @RequestBody @Valid FindIdRequestDto requestBody
    ) {
        ResponseEntity<? super ResponseDto> response = authService.findId(requestBody);
        return response;
    }

    @PostMapping("/find-password")
    public ResponseEntity<? super ResponseDto> findPassword(
        @RequestBody @Valid FindPasswordRequestDto requestBody
    ) {
        ResponseEntity<? super ResponseDto> response = authService.findPassword(requestBody);
        return response;
    }
}
