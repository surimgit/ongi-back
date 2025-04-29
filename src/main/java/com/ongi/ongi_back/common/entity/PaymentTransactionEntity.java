package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.response.payment.TossTransactionResponseDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "payment_transaction")
@Table(name = "payment_transaction")
@Getter
@NoArgsConstructor
public class PaymentTransactionEntity {

  @Id
  private String transactionKey;
  private String paymentKey;
  private String customerKey;
  private String status;
  private Integer amount;
  private String transactionTime;

  public PaymentTransactionEntity(TossTransactionResponseDto responseDto) {
    this.transactionKey = responseDto.getTransactionKey();
    this.paymentKey = responseDto.getPaymentKey();
    this.customerKey = responseDto.getCustomerKey();
    this.status = responseDto.getStatus();
    this.amount = responseDto.getAmount();
    this.transactionTime = responseDto.getTransactionAt();
  }
}