package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserNicknameResponseDto;
import com.ongi.ongi_back.common.dto.request.user.PatchResignRequestDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);
    ResponseEntity<? super GetUserNicknameResponseDto> getUserNickname(String userId);
    ResponseEntity<ResponseDto> resignUser(PatchResignRequestDto dto, String userId);
}
