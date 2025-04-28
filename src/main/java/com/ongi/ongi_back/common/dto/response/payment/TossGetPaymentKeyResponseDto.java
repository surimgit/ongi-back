package com.ongi.ongi_back.common.dto.response.payment;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossGetPaymentKeyResponseDto extends ResponseDto {
  private String orderId;
  private String status;
  private String paymentKey;
  private Map<String, String> metadata;
}
