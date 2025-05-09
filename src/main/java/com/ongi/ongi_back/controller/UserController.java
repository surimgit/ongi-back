package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.user.PatchResignRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.admin.GetIsAdminResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserNicknameResponseDto;
import com.ongi.ongi_back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<? super GetUserNicknameResponseDto> getUserNickname(
        @RequestParam(value="nickname", required=false) String reportedId,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetUserNicknameResponseDto> response = userService.getUserNickname(reportedId);
        return response;
    }

    @PatchMapping("/resign")
    public ResponseEntity<ResponseDto> resignUser(
        @RequestBody @Valid PatchResignRequestDto dto,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = userService.resignUser(dto, userId);
        return response;
    }

    @GetMapping("/is-admin")
    public ResponseEntity<? super GetIsAdminResponseDto> getIsAdmin(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetIsAdminResponseDto> response = userService.getIsAdmin(userId);
        return response;
    }
}
