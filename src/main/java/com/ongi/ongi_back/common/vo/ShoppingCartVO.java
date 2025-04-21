package com.ongi.ongi_back.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartVO {
  private Integer shoppingCartSequence;
  private Integer productSequence;
  private Integer quantity;
  private String name;
  private Integer price;
  private String image;
}
