package com.ongi.ongi_back.common.dto.request.needHelper;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostChatRequestDto {
  @NotBlank
  private String userId;
}
