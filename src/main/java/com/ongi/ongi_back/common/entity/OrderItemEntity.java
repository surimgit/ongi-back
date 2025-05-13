package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.payment.PostOrderItemRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "order_item")
@Entity(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderItemSequence;
  private String paymentKey;
  private Integer productSequence;
  private Integer quantity;
  private String waybillNumber;
  private String buyerId;
  private Integer addressId;

  public OrderItemEntity(PostOrderItemRequestDto requestDto){
    this.paymentKey = requestDto.getPaymentKey();
    this.productSequence = requestDto.getProductSequence();
    this.quantity = requestDto.getQuantity();
    this.buyerId = requestDto.getUserId();
    this.addressId = requestDto.getAddressId();
  }

  public OrderItemEntity(PostOrderItemRequestDto requestDto, String buyerId){
    this.paymentKey = requestDto.getPaymentKey();
    this.productSequence = requestDto.getProductSequence();
    this.quantity = requestDto.getQuantity();
    this.buyerId = buyerId;
  }
}
