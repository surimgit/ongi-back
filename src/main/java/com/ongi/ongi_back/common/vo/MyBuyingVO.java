package com.ongi.ongi_back.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyBuyingVO {
  private String paymentKey;
  private Integer orderItemSequence;
  private Integer productSequence;
  private String name;
  private String image;
  private Integer quantity;
  private Integer price;
  private String approvedTime;
  private String waybillNumber;
  private String deliveryAddressSnapshot;
  private String userId;
}
