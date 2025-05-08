package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishListResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishResponseDto;
import com.ongi.ongi_back.service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/wish")
@RequiredArgsConstructor
public class WishListController {

  private final WishListService wishListService;

  @PostMapping("/{productSequence}")
  public ResponseEntity<ResponseDto> postWishList(
    @PathVariable("productSequence") Integer productSequence,
    @AuthenticationPrincipal String userId
  ){
    System.out.println(userId);
    ResponseEntity<ResponseDto> response = wishListService.postWishList(userId, productSequence);
    return response;
  }

  @GetMapping("/{productSequence}")
  public ResponseEntity<? super GetWishResponseDto> getWish(
    @PathVariable("productSequence") Integer productSequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetWishResponseDto> response = wishListService.getWishByUserAndSequence(userId, productSequence);
    return response;
  }

  @GetMapping({"/", ""})
  public ResponseEntity<? super GetWishListResponseDto> getWishList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetWishListResponseDto> response = wishListService.getWishList(userId);
    return response;
  }

  @GetMapping("/count")
  public long getCountWishLen(
    @AuthenticationPrincipal String userId
  ){
    long response = wishListService.getCountWish(userId);
    return response;
  }

  @DeleteMapping("/{productSequence}")
  public ResponseEntity<ResponseDto> deleteWish(
    @PathVariable("productSequence") Integer productSequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = wishListService.deleteWishList(userId, productSequence);
    return response;
  }
}
