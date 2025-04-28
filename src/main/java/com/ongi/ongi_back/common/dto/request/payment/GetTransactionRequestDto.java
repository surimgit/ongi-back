package com.ongi.ongi_back.common.dto.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class GetTransactionRequestDto {
  @NotBlank
  private String startDate;

  @NotBlank
  private String endDate;
}
