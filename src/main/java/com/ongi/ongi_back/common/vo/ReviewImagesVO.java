package com.ongi.ongi_back.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewImagesVO {
  private Integer reviewSequence;
  private String userId;
  private Integer productSequence;
  private String reviewImage;
}
