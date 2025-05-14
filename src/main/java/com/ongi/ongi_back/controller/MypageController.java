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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchBadgeRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserPasswordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostWaybillRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.badge.GetBadgeListResponseDto;
import com.ongi.ongi_back.common.dto.response.badge.GetBadgeResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperApplyListRespeonseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetMyHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyActivityCountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMySalesResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetOrderItemResponseDto;
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

  @GetMapping("/activity")
  public ResponseEntity<? super GetMyActivityCountResponseDto> getMyActivityCount(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetMyActivityCountResponseDto> response = mypageService.getMyActivityCount(userId);
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

  @GetMapping("/sales")
  public ResponseEntity<? super GetMySalesResponseDto> getMySales(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetMySalesResponseDto> response = mypageService.getMySalesList(userId);
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


  @GetMapping("/product-sequence")
  public ResponseEntity<? super GetOrderItemResponseDto> getOrderItemsByProductSequence (
    @RequestParam("productSequence") Integer productSequence
  ){
    ResponseEntity<? super GetOrderItemResponseDto> response = mypageService.getOrderItemByProductSequence(productSequence);
    return response;
  }

  @PostMapping("/waybill")
  public ResponseEntity<ResponseDto> postWaybillNumber(
    @RequestBody @Valid PostWaybillRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = mypageService.postWaybillNumber(requestBody, userId);
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

  @PatchMapping("/badge")
  public ResponseEntity<ResponseDto> chooseBadge(
    @AuthenticationPrincipal String userId,
    @RequestBody @Valid PatchBadgeRequestDto requestBody
  ){
    ResponseEntity<ResponseDto> response = mypageService.chooseBadge(userId, requestBody);
    return response;
  }

  
  @GetMapping("/other/{userId}")
  public ResponseEntity<? super GetUserIntroductionResponseDto> getOtherUserIntroduction(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetUserIntroductionResponseDto> response = mypageService.getOtherUserIntroduction(userId);
    return response;
  }

  @GetMapping("/other/{userId}/badge")
  public ResponseEntity<? super GetBadgeResponseDto> getOtherUserBadge(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetBadgeResponseDto> response = mypageService.getOtherUserBadge(userId);
    return response;
  }

  @GetMapping("/other/{userId}/group-buying/selling")
  public ResponseEntity<? super GetProductListResponseDto> getOtherUserSellingProduct(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetProductListResponseDto> response = mypageService.getOtherUserSellingProduct(userId);
    return response;
  }

  @GetMapping("/other/{userId}/group-buying/selled")
  public ResponseEntity<? super GetProductListResponseDto> getOtherUserSelledProduct(
    @PathVariable("userId") String userId
  ){
    ResponseEntity<? super GetProductListResponseDto> response = mypageService.getOtherUserSelledProduct(userId);
    return response;
  }

  @GetMapping("/need-helper/ask")
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperRequestPost(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetMyHelperPostListResponseDto> response = mypageService.getMyHelperRequestPost(userId);
    return response;
  }

  @GetMapping("/need-helper/apply")
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperApplyPost(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetMyHelperPostListResponseDto> response = mypageService.getMyHelperApplyPost(userId);
    return response;
  }

  @GetMapping("/need-helper/liked")
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperLikedPost(
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetMyHelperPostListResponseDto> response = mypageService.getMyHelperLikedPost(userId);
    return response;
  }

  @GetMapping("/need-helper/{postSequence}/count")
  public ResponseEntity<Integer> getApplicantCount(
    @PathVariable("postSequence") Integer postSequence,
    @AuthenticationPrincipal String userId
  ) {
    int count = mypageService.getApplicantCount(postSequence, userId);
    return ResponseEntity.ok(count);
  }

  @GetMapping("/need-helper/{postSequence}/apply")
  public ResponseEntity<? super GetHelperApplyListRespeonseDto> getHelperApplyList(
    @PathVariable("postSequence") Integer postSequence,
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<? super GetHelperApplyListRespeonseDto> response = mypageService.getHelperApplyList(userId, postSequence);
    return response;
  }

  @GetMapping("/other/{userId}/community/post")
  public ResponseEntity<? super GetCommunityResponseDto> getOtherUserCommunityPost(
    @PathVariable("userId") String userId  
  ) {
    ResponseEntity<? super GetCommunityResponseDto> response = mypageService.getOtherUserCommunityPost(userId);
    return response;
  }
  
  @GetMapping("/other/{userId}/need-helper")
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getOtherUserHelperPost(
    @PathVariable("userId") String userId  
  ) {
    ResponseEntity<? super GetMyHelperPostListResponseDto> response = mypageService.getOtherUserHelperPost(userId);
    return response;
  }

  @GetMapping("/other/{userId}/need-helper/{postSequence}/count")
  public ResponseEntity<Integer> getOtherApplicantCount(
    @PathVariable("postSequence") Integer postSequence,
    @PathVariable("userId") String userId  
  ) {
    int count = mypageService.getApplicantCount(postSequence, userId);
    return ResponseEntity.ok(count);
  }

  @GetMapping("/other/{userId}/need-helper/{postSequence}/comments")
  public ResponseEntity<? super GetHelperCommentsResponseDto> getOtherUserHelperComments(
    @PathVariable("postSequence") Integer postSequence,
    @PathVariable("userId") String userId  
  )  {
    ResponseEntity<? super GetHelperCommentsResponseDto> response = mypageService.getOtherUserHelperComments(postSequence, userId);
    return response;
  }

  @GetMapping("/other/{userId}/group-buying")
  public ResponseEntity<? super GetMySalesResponseDto> getOtherUserSale(
    @PathVariable("userId") String userId  
  ){
    ResponseEntity<? super GetMySalesResponseDto> response = mypageService.getMySalesList(userId);
    return response;
  }
  
}
