package com.ongi.ongi_back.common.dto.response.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.vo.ReviewImagesVO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetReviewImagesResponseDto extends ResponseDto {
  List<ReviewImagesVO> reviewImages;

  private GetReviewImagesResponseDto(List<ReviewImagesVO> reviewImagesEntities) {
    this.reviewImages = reviewImagesEntities;
  }

  public static ResponseEntity<GetReviewImagesResponseDto> success(List<ReviewImagesVO> reviewImagesEntities){
    GetReviewImagesResponseDto body = new GetReviewImagesResponseDto(reviewImagesEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
