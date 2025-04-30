package com.ongi.ongi_back.common.vo;

import com.ongi.ongi_back.common.entity.OrderItemEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemVO {
  private Integer orderItemSequence;
  private Integer productSequence;
  private Integer quantity;
  private String waybillNumber;

  public OrderItemVO(OrderItemEntity orderItemEntity){
    this.orderItemSequence = orderItemEntity.getOrderItemSequence();
    this.productSequence = orderItemEntity.getProductSequence();
    this.quantity = orderItemEntity.getQuantity();
    this.waybillNumber = orderItemEntity.getWaybillNumber();
  }
}
