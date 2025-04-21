package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAccountRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;


public interface MypageService {
  ResponseEntity<ResponseDto> patchIntroduction(PatchUserIntroductionRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchUserAccount(PatchUserAccountRequestDto dto, String userId);
  ResponseEntity<ResponseDto> addLikeKeyword(AddLikeKeywordRequestDto dto, String userId);
  ResponseEntity<ResponseDto> deleteLikeKeyword(DeleteLikeKeywordRequestDto dto, String userId);

  ResponseEntity<? super GetUserIntroductionResponseDto> getUserIntroduction(String userId);
  ResponseEntity<? super GetUserAccountResponseDto> getUserAccount(String userId);
  ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeywordList(String userId);

}