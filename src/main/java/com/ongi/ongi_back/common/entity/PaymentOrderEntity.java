package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
  private String phoneNumber;
  private String userName;
  private String createdAt;
  private Integer addressId;

  public PaymentOrderEntity(PostOrderRequestDto dto, String userId){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.orderId = dto.getOrderId();
    this.userId = userId;
    this.amount = dto.getAmount();
    this.buyerAddress = dto.getBuyerAddress();
    this.phoneNumber = dto.getPhoneNumber();
    this.userName = dto.getUserName();
    this.createdAt = now.format(dateTimeFormatter);
    this.addressId = dto.getAddressId();
  }
}
