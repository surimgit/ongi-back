package com.ongi.ongi_back.common.dto.request.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchQuestionRequestDto {
  @NotBlank
  @Pattern(regexp="^배송|커뮤니티|계정|광고|제안$")
  private String category;

  @NotBlank
  private String title;

  @NotBlank
  private String content;
}
