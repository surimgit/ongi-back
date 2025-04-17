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
  private String filterType;

  public GetProductListResponseDto(List<ProductEntity> productEntities, String filterType){
    this.products = ProductVO.getList(productEntities);
    this.filterType = filterType;
  }
  
  public static ResponseEntity<GetProductListResponseDto> success(List<ProductEntity> productEntities, String filterType) {
    GetProductListResponseDto body = new GetProductListResponseDto(productEntities, filterType);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
