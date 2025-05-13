package com.ongi.ongi_back.common.dto.response.shoppingCart;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.vo.UserAddressVO;

import lombok.Getter;

@Getter
public class GetUserAddressResponseDto extends ResponseDto {
  private List<UserAddressVO> addressLabelList;

  private GetUserAddressResponseDto(List<UserAddressVO> addressLabelList){
    this.addressLabelList = addressLabelList;
  }

  public static ResponseEntity<GetUserAddressResponseDto> success (List<UserAddressVO> addressLabelList){
    GetUserAddressResponseDto body = new GetUserAddressResponseDto(addressLabelList);

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}


