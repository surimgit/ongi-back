package com.ongi.ongi_back.common.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostWaybillRequestDto {
  @NotNull
  private Integer orderItemSequence;
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]{9,14}$")
  private String waybillNumber;
}
