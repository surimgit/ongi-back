package com.ongi.ongi_back.common.dto.request.user;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostProductReviewRequestDto {
  @NotNull
  private Integer productSequence;
  @NotNull
  private Integer rating;
  @NotBlank
  private String content;
  
  private MultipartFile[] reviewImages;
}
