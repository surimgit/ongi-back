package com.ongi.ongi_back.common.dto.response.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.vo.ProductVO;

import lombok.Getter;

@Getter
public class GetTop10ProductListDto extends ResponseDto {
  List<ProductVO> topTenList;

  private GetTop10ProductListDto(List<ProductVO> topTenList){
    this.topTenList = topTenList;
  }

  public static ResponseEntity<GetTop10ProductListDto> success(List<ProductVO> topTenList){
    GetTop10ProductListDto body = new GetTop10ProductListDto(topTenList);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
