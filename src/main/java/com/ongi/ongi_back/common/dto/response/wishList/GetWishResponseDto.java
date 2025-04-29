package com.ongi.ongi_back.common.dto.response.wishList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.WishListEntity;

import lombok.Getter;

@Getter
public class GetWishResponseDto extends ResponseDto {
  private WishListEntity wishListEntity;

  private GetWishResponseDto(WishListEntity wishListEntity){
    this.wishListEntity = wishListEntity;
  }

  public static ResponseEntity<GetWishResponseDto> success(WishListEntity wishListEntity){
    GetWishResponseDto body = new GetWishResponseDto(wishListEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
