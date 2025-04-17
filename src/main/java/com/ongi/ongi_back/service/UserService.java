package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserRequestDto;
import com.ongi.ongi_back.common.dto.request.user.UpdateIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetProfileResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;


public interface UserService {
  ResponseEntity<ResponseDto> updateIntroduction(UpdateIntroductionRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId);
  ResponseEntity<ResponseDto> addLikeKeyword(AddLikeKeywordRequestDto dto, String userId);
  ResponseEntity<ResponseDto> deleteLikeKeyword(DeleteLikeKeywordRequestDto dto, String userId);

  ResponseEntity<? super GetSignInUserResponseDto> getUserProfileInformation(String userId);
  ResponseEntity<? super GetProfileResponseDto> getUserProfile(String userId);
  ResponseEntity<? super GetSignInUserResponseDto> getUserSetting(String userId);
  ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeyword(String userId);
}
