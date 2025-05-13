package com.ongi.ongi_back.common.dto.response.shoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserAddressResponseDto extends ResponseDto {
  private Integer addressId;

  public static ResponseEntity<PostUserAddressResponseDto> success(Integer addressId) {
    return ResponseEntity.status(HttpStatus.OK).body(new PostUserAddressResponseDto(addressId));
  }
}
