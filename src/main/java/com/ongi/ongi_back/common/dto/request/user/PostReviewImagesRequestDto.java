package com.ongi.ongi_back.common.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostReviewImagesRequestDto {
  @NotNull
  private Integer reviewSequence;
  @NotNull
  private Integer imageNumber;
  @NotBlank
  private String reviewImage;
}
