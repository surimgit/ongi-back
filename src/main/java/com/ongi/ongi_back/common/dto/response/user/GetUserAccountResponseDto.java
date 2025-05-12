package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserAccountResponseDto extends ResponseDto{
  private String userId;
  private String telNumber;
  private String address;
  private String detailAddress;


  private GetUserAccountResponseDto(String userId, String telNumber, String address, String detailAddress){
    this.userId = userId;
    this.telNumber = telNumber;
    this.address = address;
    this.detailAddress = detailAddress;
  }

    public static ResponseEntity<GetUserAccountResponseDto> success(UserEntity userEntity){
      GetUserAccountResponseDto body = new GetUserAccountResponseDto(
      userEntity.getUserId(),
      userEntity.getTelNumber(),
      userEntity.getAddress(),
      userEntity.getDetailAddress());

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }


}
