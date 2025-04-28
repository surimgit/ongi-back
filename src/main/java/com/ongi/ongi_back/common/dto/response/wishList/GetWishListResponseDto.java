package com.ongi.ongi_back.common.dto.response.wishList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.WishListEntity;

import lombok.Getter;

@Getter
public class GetWishListResponseDto extends ResponseDto {
  private List<WishListEntity> wishListEntities;

  private GetWishListResponseDto(List<WishListEntity> wishListEntities){
    this.wishListEntities = wishListEntities;
  }

  public static ResponseEntity<GetWishListResponseDto> success(List<WishListEntity> wishListEntities){
    GetWishListResponseDto body = new GetWishListResponseDto(wishListEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
