package com.ongi.ongi_back.common.dto.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossTransactionResponseDto extends ResponseDto {
  private String transactionKey;
  private String paymentKey;
  private String customerKey;
  private String status;
  private Integer amount;
  private String transactionAt;
}
