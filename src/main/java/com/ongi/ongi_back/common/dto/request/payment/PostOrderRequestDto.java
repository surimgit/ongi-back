package com.ongi.ongi_back.common.dto.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostOrderRequestDto {
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9_-]{6,64}$")
  private String orderId;

  @NotNull
  private Integer amount;

  @NotBlank
  private String buyerAddress;
}
