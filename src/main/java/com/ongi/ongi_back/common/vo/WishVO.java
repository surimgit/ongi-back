package com.ongi.ongi_back.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishVO {
  private String name;
  private Integer price;
  private Integer sequence;
  private Integer remainingProducts;
  private String deadline;
  private boolean isSoldOut;
  private String openDate;
  private String image;
}

