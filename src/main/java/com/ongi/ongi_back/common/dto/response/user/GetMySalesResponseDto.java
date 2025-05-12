package com.ongi.ongi_back.common.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.vo.MySalesVO;

import lombok.Getter;

@Getter
public class GetMySalesResponseDto extends ResponseDto {
  List<MySalesVO> mySales;

  private GetMySalesResponseDto(List<MySalesVO> mySales){
    this.mySales = mySales;
  }

  public static ResponseEntity<GetMySalesResponseDto> success(List<MySalesVO> mySales){
    GetMySalesResponseDto body = new GetMySalesResponseDto(mySales);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
