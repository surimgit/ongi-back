package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Entity(name = "payment_confirm")
@Table(name = "payment_confirm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmEntity {
  @Id
  private String paymentKey;
  private String orderId;
  private Integer amount;
  private String status;
  private String approvedTime;

  public PaymentConfirmEntity(PostConfirmRequestDto dto){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    this.paymentKey = dto.getPaymentKey();
    this.orderId = dto.getOrderId();
    this.amount = dto.getAmount();
    this.status = dto.getStatus();
    this.approvedTime = now.format(dateTimeFormatter);
  }
}
