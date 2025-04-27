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
  private Integer orderItemSequence;
  private String name;
  private String image;
  private Integer quantity;
  private Integer price;
  private String approvedTime;
}
