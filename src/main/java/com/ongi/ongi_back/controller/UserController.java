package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAccountRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PatchMapping({"", "/"})
  public ResponseEntity<ResponseDto> patchIntroduction(
    @RequestBody @Valid PatchUserIntroductionRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.patchIntroduction(requestBody, userId);
    return response;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<? super GetUserIntroductionResponseDto> getUserIntroduction(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserIntroductionResponseDto> response = userService.getUserIntroduction(userId);
    return response;
  }

  @GetMapping("/other/{userId}")
  public ResponseEntity<? super GetUserIntroductionResponseDto> getOtherUserIntroduction(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetUserIntroductionResponseDto> response = userService.getUserIntroduction(userId);
    return response;
  }


  @GetMapping("/keyword")
  public ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeywordList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetLikeKeywordListResponseDto> response = userService.getLikeKeywordList(userId);
    return response;
  }

  
  @PostMapping("/keyword")
  public ResponseEntity<ResponseDto> addLikeKeyword(
    @RequestBody @Valid AddLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.addLikeKeyword(requestBody, userId);
    return response;
  }

  @DeleteMapping("/keyword")
  public ResponseEntity<ResponseDto> deleteLikeKeyword(
    @RequestBody @Valid DeleteLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.deleteLikeKeyword(requestBody, userId);
    return response;
  }
  

  @PatchMapping("/account")
  public ResponseEntity<ResponseDto> patchUserAccount(
    @RequestBody @Valid PatchUserAccountRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = userService.patchUserAccount(requestBody, userId);
    return response;
  }

  @GetMapping("/account")
  public ResponseEntity<? super GetUserAccountResponseDto> getUserAccount(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserAccountResponseDto> response = userService.getUserAccount(userId);
    return response;
  }
}
