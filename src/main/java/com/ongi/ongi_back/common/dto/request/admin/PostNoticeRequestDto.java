package com.ongi.ongi_back.common.dto.request.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostNoticeRequestDto {
  @NotBlank
  private String title;

  @NotBlank
  private String content;
}
