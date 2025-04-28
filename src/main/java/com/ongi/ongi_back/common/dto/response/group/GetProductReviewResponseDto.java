package com.ongi.ongi_back.common.dto.response.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductReviewEntity;
import com.ongi.ongi_back.common.vo.ProductReviewVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductReviewResponseDto extends ResponseDto {
  List<ProductReviewVO> productReviews;

  public GetProductReviewResponseDto(List<ProductReviewEntity> productReviewEntities) {
    this.productReviews = ProductReviewVO.getList(productReviewEntities);
  }

  public static ResponseEntity<GetProductReviewResponseDto> success(List<ProductReviewEntity> productReviewEntities){
    GetProductReviewResponseDto body = new GetProductReviewResponseDto(productReviewEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);

  }

  
}
