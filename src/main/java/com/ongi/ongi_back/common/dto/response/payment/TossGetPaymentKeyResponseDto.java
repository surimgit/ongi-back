package com.ongi.ongi_back.common.dto.response.payment;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossGetPaymentKeyResponseDto {
  private String orderId;
  private String status;
  private String paymentKey;
  private Map<String, String> metadata;
}
