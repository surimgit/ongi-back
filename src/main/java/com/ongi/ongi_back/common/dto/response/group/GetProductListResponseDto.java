package com.ongi.ongi_back.common.dto.response.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.vo.ProductVO;

import lombok.Getter;

@Getter
public class GetProductListResponseDto extends ResponseDto {
  
  private List<ProductVO> products;

  public GetProductListResponseDto(List<ProductEntity> productEntities){
    this.products = ProductVO.getList(productEntities);
  }

  public static ResponseEntity<GetProductListResponseDto> success(List<ProductEntity> productEntities) {
    GetProductListResponseDto body = new GetProductListResponseDto(productEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
