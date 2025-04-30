package com.ongi.ongi_back.common.vo;

import com.ongi.ongi_back.common.entity.ProductEntity;

import lombok.Getter;

@Getter
public class MySalesVO {
  private Integer sequence;
  private String category;
  private String name;
  private Integer price;
  private Integer productQuantity;
  private Integer boughtAmount;
  private String deadline;
  private String openDate;
  private String image;
  private Boolean isSoldOut;

  public MySalesVO(ProductEntity productEntity){
    this.sequence = productEntity.getSequence();
    this.category = productEntity.getCategory();
    this.name = productEntity.getName();
    this.price = productEntity.getPrice();
    this.productQuantity = productEntity.getProductQuantity();
    this.boughtAmount = productEntity.getBoughtAmount();
    this.deadline = productEntity.getDeadline();
    this.openDate = productEntity.getOpenDate();
    this.image = productEntity.getImage();
    this.isSoldOut = productEntity.getIsSoldOut();
  }
}
