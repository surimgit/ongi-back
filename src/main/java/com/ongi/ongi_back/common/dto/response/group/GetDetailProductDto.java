package com.ongi.ongi_back.common.dto.response.group;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;

import lombok.Getter;

@Getter
public class GetDetailProductDto extends ResponseDto {
  private String image;
  private String name;
  private String userId;
  private Integer price;
  private String category;
  private Integer productQuantity;
  private Integer boughtAmount;
  private Integer purchasedPeople;
  private String deadline;
  private Boolean isSoldOut;
  private String content;
  private String openDate;
  private String status;

  private GetDetailProductDto(ProductEntity productEntity) {
    this.image = productEntity.getImage();
    this.name = productEntity.getName();
    this.userId = productEntity.getUserId();
    this.price = productEntity.getPrice();
    this.category = productEntity.getCategory();
    this.productQuantity = productEntity.getProductQuantity();
    this.boughtAmount = productEntity.getBoughtAmount();
    this.purchasedPeople = productEntity.getPurchasedPeople();
    this.deadline = productEntity.getDeadline();
    this.isSoldOut = productEntity.getIsSoldOut();
    this.content = productEntity.getContent();
    this.openDate = productEntity.getOpenDate();
    this.status = productEntity.getStatus();
  }

  public static ResponseEntity<GetDetailProductDto> success(ProductEntity productEntity){
    GetDetailProductDto body = new GetDetailProductDto(productEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
