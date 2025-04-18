package com.ongi.ongi_back.common.dto.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossCancelResponseDto {
  private String paymentKey;
  private String lastTransactionKey;
}
