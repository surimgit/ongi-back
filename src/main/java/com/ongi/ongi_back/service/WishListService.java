package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishListResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishResponseDto;

public interface WishListService {

  ResponseEntity<ResponseDto> postWishList(String userId, Integer productSequence);
  ResponseEntity<? super GetWishListResponseDto> getWishList(String userId);
  ResponseEntity<? super GetWishResponseDto > getWishByUserAndSequence(String userId, Integer productSequence);
  ResponseEntity<ResponseDto> deleteWishList(String userId, Integer productSequence);
} 
