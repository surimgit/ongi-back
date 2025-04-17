package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserRequestDto;
import com.ongi.ongi_back.common.dto.request.user.UpdateIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetProfileResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;
import com.ongi.ongi_back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PatchMapping({"", "/"})
  public ResponseEntity<ResponseDto> updateIntroduction(
    @RequestBody @Valid UpdateIntroductionRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.updateIntroduction(requestBody, userId);
    return response;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<? super GetProfileResponseDto> getProfile(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetProfileResponseDto> response = userService.getUserProfile(userId);
    return response;
  }

  @PostMapping({"", "/"})
  public ResponseEntity<ResponseDto> addLikeKeyword(
    @RequestBody @Valid AddLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.addLikeKeyword(requestBody, userId);
    return response;
  }

  @DeleteMapping({"","/"})
  public ResponseEntity<ResponseDto> deleteLikeKeyword(
    @RequestBody @Valid DeleteLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.deleteLikeKeyword(requestBody, userId);
    return response;
  }
  
  @PatchMapping("/setting")
  public ResponseEntity<ResponseDto> patchUser(
    @RequestBody @Valid PatchUserRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.patchUser(requestBody, userId);
    return response;
  }

  @GetMapping("/setting")
  public ResponseEntity<? super GetSignInUserResponseDto> getSetting(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetSignInUserResponseDto> response = userService.getUserSetting(userId);
    return response;
  }

}
