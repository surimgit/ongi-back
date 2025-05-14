package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetMyActivityCountResponseDto extends ResponseDto {
  private Integer communityCommentCount;
  private Integer communityPostCount;
  private Integer applyCount;
  private Integer acceptCount;
  private Integer shoppingCartCount;
  private long wishListCount;

  private GetMyActivityCountResponseDto(
  Integer communityCommentCount,
  Integer communityPostCount,
  Integer applyCount,
  Integer acceptCount,
  Integer shoppingCartCount,
  long wishListCount){
    this.communityCommentCount = communityCommentCount;
    this.communityPostCount = communityPostCount;
    this.applyCount = applyCount;
    this.acceptCount = acceptCount;
    this.shoppingCartCount = shoppingCartCount;
    this.wishListCount = wishListCount;
  }

  public static ResponseEntity<GetMyActivityCountResponseDto> success(Integer communityCommentCount, 
  Integer communityPostCount,
  Integer applyCount,
  Integer acceptCount,
  Integer shoppingCartCount,
  long wishListCount
  ){
    GetMyActivityCountResponseDto body = new GetMyActivityCountResponseDto(communityCommentCount, 
    communityPostCount,
    applyCount,
    acceptCount,
    shoppingCartCount,
    wishListCount);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
