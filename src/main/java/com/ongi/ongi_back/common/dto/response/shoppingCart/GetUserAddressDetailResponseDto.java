package com.ongi.ongi_back.common.dto.response.shoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserAddressEntity;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class GetUserAddressDetailResponseDto extends ResponseDto {
  private String addressLabel;
  private String recipientName;
  private String phone;
  private String zipcode;
  private String address;
  private String detailAddress;

  public GetUserAddressDetailResponseDto(UserAddressEntity userAddressEntity){
    this.addressLabel = userAddressEntity.getAddressLabel();
    this.recipientName = userAddressEntity.getRecipientName();
    this.phone = userAddressEntity.getPhone();
    this.zipcode = userAddressEntity.getZipcode();
    this.address = userAddressEntity.getAddress();
    this.detailAddress = userAddressEntity.getDetailAddress();
  }

  public static ResponseEntity<GetUserAddressDetailResponseDto> success(UserAddressEntity userAddressEntity){
    GetUserAddressDetailResponseDto body = new GetUserAddressDetailResponseDto(userAddressEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
