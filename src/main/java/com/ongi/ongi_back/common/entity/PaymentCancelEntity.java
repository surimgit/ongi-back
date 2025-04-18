package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "payment_cancel")
@Table(name = "payment_cancel")
@Getter
public class PaymentCancelEntity {

  @Id
  private String paymentKey;
  private Integer cancelAmount;
  private String cancelReason;
  private String canceledTime;
  private String status;

  public PaymentCancelEntity(PostCancelRequestDto dto){
    this.paymentKey = dto.getPaymentKey();
    
  }
}
