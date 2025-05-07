package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.ProductEntity;

import lombok.Getter;

@Getter
public class ProductVO {
  private Integer sequence;
  private String name;
  private String category;
  private Integer price;
  private double rating;
  private Integer purchasedPeople;
  private String deadline;
  private Integer boughtAmount;
  private Integer productQuantity;
  private String image;
  private String openDate;
  private String status;

  public ProductVO(ProductEntity productEntity) {
    this.sequence = productEntity.getSequence();
    this.name = productEntity.getName();
    this.category = productEntity.getCategory();
    this.price = productEntity.getPrice();
    this.purchasedPeople = productEntity.getPurchasedPeople();
    this.deadline = productEntity.getDeadline();
    this.boughtAmount = productEntity.getBoughtAmount();
    this.productQuantity = productEntity.getProductQuantity();
    this.image = productEntity.getImage();
    this.openDate = productEntity.getOpenDate();
    this.status = productEntity.getStatus();
  }

  public static List<ProductVO> getList(List<ProductEntity> productEntities){
    List<ProductVO> list = new ArrayList<>();
    for(ProductEntity productEntity: productEntities) {
      ProductVO productVO = new ProductVO(productEntity);
      list.add(productVO);
    }

    return list;
  }
}
