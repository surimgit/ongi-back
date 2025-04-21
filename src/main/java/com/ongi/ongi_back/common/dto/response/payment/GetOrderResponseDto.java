package com.ongi.ongi_back.common.dto.response.payment;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.PaymentOrderEntity;

import lombok.Getter;

@Getter
public class GetOrderResponseDto extends ResponseDto {
  private String orderId;
  private Integer amount;
  private String phoneNumber;
  private String userName;

  public GetOrderResponseDto(PaymentOrderEntity paymentOrderEntity){
    this.orderId = paymentOrderEntity.getOrderId();
    this.amount = paymentOrderEntity.getAmount();
    this.phoneNumber = paymentOrderEntity.getPhoneNumber();
    this.userName = paymentOrderEntity.getUserName();
  }

  public static ResponseEntity<GetOrderResponseDto> success(PaymentOrderEntity paymentOrderEntity){
    GetOrderResponseDto body = new GetOrderResponseDto(paymentOrderEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
