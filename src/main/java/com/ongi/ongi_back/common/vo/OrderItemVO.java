package com.ongi.ongi_back.common.vo;

import com.ongi.ongi_back.common.entity.OrderItemEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemVO {
  private Integer orderItemSequence;
  private Integer productSequence;
  private Integer quantity;
  private String waybillNumber;
  private String deliveryAddressSnapshot;
  private String approvedTime;
  private String buyerId;
  private Integer addressId;

  public OrderItemVO(OrderItemEntity orderItemEntity){
    this.orderItemSequence = orderItemEntity.getOrderItemSequence();
    this.productSequence = orderItemEntity.getProductSequence();
    this.quantity = orderItemEntity.getQuantity();
    this.waybillNumber = orderItemEntity.getWaybillNumber();
    this.deliveryAddressSnapshot = orderItemEntity.getDeliveryAddressSnapshot();
    this.buyerId = orderItemEntity.getBuyerId();
    this.addressId = orderItemEntity.getAddressId();
  }
}
