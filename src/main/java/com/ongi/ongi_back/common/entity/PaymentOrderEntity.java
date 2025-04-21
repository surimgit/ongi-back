package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "payment_order")
@Table(name = "payment_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderEntity {
  @Id
  private String orderId;
  private String userId;
  private Integer amount;
  private String buyerAddress;

  public PaymentOrderEntity(PostOrderRequestDto dto, String userId){
    this.orderId = dto.getOrderId();
    this.userId = userId;
    this.amount = dto.getAmount();
    this.buyerAddress = dto.getBuyerAddress();
  }
}
