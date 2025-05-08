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
import com.ongi.ongi_back.common.dto.request.user.PatchUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserPasswordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.badge.GetBadgeListResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.service.MypageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {
  private final MypageService mypageService;

  @PatchMapping({"", "/"})
  public ResponseEntity<ResponseDto> patchIntroduction(
    @RequestBody @Valid PatchUserIntroductionRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.patchIntroduction(requestBody, userId);
    return response;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<? super GetUserIntroductionResponseDto> getUserIntroduction(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserIntroductionResponseDto> response = mypageService.getUserIntroduction(userId);
    return response;
  }

  @GetMapping("/other/{userId}")
  public ResponseEntity<? super GetUserIntroductionResponseDto> getOtherUserIntroduction(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetUserIntroductionResponseDto> response = mypageService.getOtherUserIntroduction(userId);
    return response;
  }


  @GetMapping("/keyword")
  public ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeywordList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetLikeKeywordListResponseDto> response = mypageService.getLikeKeywordList(userId);
    return response;
  }

  
  @PostMapping("/keyword")
  public ResponseEntity<ResponseDto> addLikeKeyword(
    @RequestBody @Valid AddLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.addLikeKeyword(requestBody, userId);
    return response;
  }

  @DeleteMapping("/keyword")
  public ResponseEntity<ResponseDto> deleteLikeKeyword(
    @RequestBody @Valid DeleteLikeKeywordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.deleteLikeKeyword(requestBody, userId);
    return response;
  }
  

  @PatchMapping("/account/patch")
  public ResponseEntity<ResponseDto> patchUserPassword(
    @RequestBody @Valid PatchUserPasswordRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.patchUserPassword(requestBody, userId);
    return response;
  }

  @PatchMapping("/account")
  public ResponseEntity<ResponseDto> patchUserAddress(
    @RequestBody @Valid PatchUserAddressRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.patchUserAddress(requestBody, userId);
    return response;
  }


  @GetMapping("/account")
  public ResponseEntity<? super GetUserAccountResponseDto> getUserAccount(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserAccountResponseDto> response = mypageService.getUserAccount(userId);
    return response;
  }


  @GetMapping("/community/post")
  public ResponseEntity<? super GetCommunityResponseDto> getMyCommunityPost(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetCommunityResponseDto> response = mypageService.getMyCommunityPost(userId);
    return response;
  }
  
  @GetMapping("/community/comment")
  public ResponseEntity<? super GetCommunityCommentsResponseDto> getMyCommunityComment(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetCommunityCommentsResponseDto> response = mypageService.getMyCommunityComment(userId);
    return response;
  }
  
  @GetMapping("/community/liked")
  public ResponseEntity<? super GetCommunityResponseDto> getMyCommunityLikedPostComment(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetCommunityResponseDto> response = mypageService.getMyCommunityLikedPostComment(userId);
    return response;
  }
  
  @GetMapping("/buy/my")
  public ResponseEntity<? super GetMyBuyingResponseDto> getMyPurchaseList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetMyBuyingResponseDto> response = mypageService.getMyPurchaseList(userId);
    return response;
  }

  @PostMapping("buy/my/review")
  public ResponseEntity<ResponseDto> postPurchaseReview(
    @RequestBody @Valid PostProductReviewRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.postProductReview(requestBody, userId);
    return response;
  }

  @PostMapping("/badge")
  public ResponseEntity<ResponseDto> addBadge(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.addBadge(userId);
    return response;
  }

  
  @GetMapping("/badge")
  public ResponseEntity<? super GetBadgeListResponseDto> getBadgeList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetBadgeListResponseDto> response = mypageService.getBadgeList(userId);
    return response;
  }
}
