package com.ongi.ongi_back.common.entity;

import java.util.Map;

import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.vo.TossCancel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "payment_cancel")
@Table(name = "payment_cancel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCancelEntity {

  @Id
  private String paymentKey;
  private String transactionKey;
  private Integer cancelAmount;
  private String cancelReason;
  private String canceledTime;
  private String status;
  private Integer productSequence;

  public PaymentCancelEntity(TossCancel cancels, String paymentKey, String cancelReason, Integer productSequence){
    this.paymentKey = paymentKey;
    this.cancelReason = cancelReason;
    this.transactionKey = cancels.getTransactionKey();
    this.cancelAmount = cancels.getCancelAmount();
    this.canceledTime = cancels.getCanceledAt();
    this.status = cancels.getCancelStatus();
    this.productSequence = productSequence;
  }
}
