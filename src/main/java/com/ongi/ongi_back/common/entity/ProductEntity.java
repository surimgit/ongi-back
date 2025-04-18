package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer sequence;
  private String name;
  private String userId;
  private Integer price;
  private String category;
  private String content;
  private Integer productQuantity;
  private Integer boughtAmount;
  private Integer purchasedPeople;
  private String deadline;
  private Boolean isSoldOut;
  private String image;
  private Integer adPayment;
  private String openDate;

  public ProductEntity(PostProductRequestDto dto, String userId) {
    this.name = dto.getName();
    this.userId = userId;
    this.price = dto.getPrice();
    this.category = dto.getCategory();
    this.content = dto.getContent();
    this.productQuantity = dto.getProductQuantity();
    this.boughtAmount = dto.getBoughtAmount();
    this.purchasedPeople = dto.getPurchasedPeople();
    this.deadline = dto.getDeadline();
    this.isSoldOut = dto.getIsSoldOut();
    this.image = dto.getImage();
    this.adPayment = dto.getAdPayment();
    this.openDate = dto.getOpenDate();
  }

  public void patch(PatchProductRequestDto dto) {
    this.name = dto.getName();
    this.price = dto.getPrice();
    this.category = dto.getCategory();
    this.content = dto.getContent();
    this.productQuantity = dto.getProductQuantity();
    this.deadline = dto.getDeadline();
    this.image = dto.getImage();
  }
}
