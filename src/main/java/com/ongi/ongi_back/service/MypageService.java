package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

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
import com.ongi.ongi_back.common.dto.response.needHelper.GetMyHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyActivityCountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMySalesResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetOrderItemResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;


public interface MypageService {
  ResponseEntity<ResponseDto> patchIntroduction(PatchUserIntroductionRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchUserPassword(PatchUserPasswordRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchUserAddress(PatchUserAddressRequestDto dto, String userId);
  ResponseEntity<ResponseDto> addLikeKeyword(AddLikeKeywordRequestDto dto, String userId);
  ResponseEntity<ResponseDto> deleteLikeKeyword(DeleteLikeKeywordRequestDto dto, String userId);

  ResponseEntity<? super GetUserIntroductionResponseDto> getUserIntroduction(String userId);
  ResponseEntity<? super GetUserAccountResponseDto> getUserAccount(String userId);
  ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeywordList(String userId);

  ResponseEntity<? super GetCommunityResponseDto> getMyCommunityPost(String userId);
  ResponseEntity<? super GetCommunityCommentsResponseDto> getMyCommunityComment(String userId);
  ResponseEntity<? super GetCommunityResponseDto> getMyCommunityLikedPostComment(String userId);
  ResponseEntity<? super GetMyActivityCountResponseDto> getMyActivityCount(String userId);

  ResponseEntity<? super GetMyBuyingResponseDto> getMyPurchaseList(String userId);
  ResponseEntity<? super GetProductListResponseDto> getMySelledList(String userId);
  ResponseEntity<? super GetProductListResponseDto> getMyWishList(String userId);
  
  ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperRequestPost(String userId);
  ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperApplyPost(String userId);
  ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperLikedPost(String userId);
  Integer getApplicantCount(Integer postSequence, String userId);
  ResponseEntity<? super GetHelperApplyListRespeonseDto> getHelperApplyList(String userId, Integer postSequence);

  ResponseEntity<ResponseDto> postProductReview(PostProductReviewRequestDto dto, String userId);
  ResponseEntity<? super GetMySalesResponseDto> getMySalesList(String userId);
  ResponseEntity<? super GetOrderItemResponseDto> getOrderItemByProductSequence(Integer sequence);
  ResponseEntity<ResponseDto> postWaybillNumber(PostWaybillRequestDto dto, String userId);

  // 뱃지
  ResponseEntity<ResponseDto> addBadge(String userId);
  ResponseEntity<? super GetBadgeListResponseDto> getBadgeList(String userId);
  ResponseEntity<ResponseDto> chooseBadge(String userId, PatchBadgeRequestDto dto);

  // 다른 사용자
  ResponseEntity<? super GetUserIntroductionResponseDto> getOtherUserIntroduction(String userId);
  ResponseEntity<? super GetBadgeResponseDto> getOtherUserBadge(String userId);
  ResponseEntity<? super GetCommunityCommentsResponseDto> getOtherUserCommunityComment(String userId);
  ResponseEntity<? super GetCommunityResponseDto> getOtherUserCommunityPost(String userId);
  ResponseEntity<? super GetMyHelperPostListResponseDto> getOtherUserHelperPost(String userId);
  ResponseEntity<? super GetHelperCommentsResponseDto> getOtherUserHelperComments(Integer postSequence, String userId);
  ResponseEntity<? super GetProductListResponseDto> getOtherUserSellingProduct(String userId);
  ResponseEntity<? super GetProductListResponseDto> getOtherUserSelledProduct(String userId);
  // ResponseEntity<? super GetProductReviewResponseDto> getOtherUserProductReviewed(String userId);

}
