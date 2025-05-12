package com.ongi.ongi_back.common.dto.request.shoppingCart;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostUserAddressRequestDto {
  @NotBlank
  private String recipientName;
  @NotBlank
  private String addressLabel;
  @NotBlank
  private String phone;
  @NotBlank
  private String zipcode;
  @NotBlank
  private String address;
  
  private String detailAddress;
  private String addressType;
}
