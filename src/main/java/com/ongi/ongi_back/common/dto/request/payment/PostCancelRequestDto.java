package com.ongi.ongi_back.common.dto.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCancelRequestDto {
  @NotBlank
  private String paymentKey;

  @NotBlank
  private String cancelReason;

  @NotNull
  private Integer cancelAmount;

  @NotNull
  private Integer productSequence; 
}
