package com.ongi.ongi_back.common.entity;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  private String status;

  public ProductEntity(PostProductRequestDto dto, String userId) {
    LocalDateTime deadlineDateTime = LocalDateTime.parse(dto.getDeadline(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime openDateTime = LocalDateTime.parse(dto.getOpenDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    this.name = dto.getName();
    this.userId = userId;
    this.price = dto.getPrice();
    this.category = dto.getCategory();
    this.content = dto.getContent();
    this.productQuantity = dto.getProductQuantity();
    this.boughtAmount = dto.getBoughtAmount();
    this.purchasedPeople = dto.getPurchasedPeople();
    this.deadline = deadlineDateTime.format(dateTimeFormatter);
    this.isSoldOut = dto.getIsSoldOut();
    this.image = dto.getImage();
    this.adPayment = dto.getAdPayment();
    this.openDate = openDateTime.format(dateTimeFormatter);
    this.status = getStatus(deadlineDateTime, openDateTime);
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

  public String getStatus(LocalDateTime deadlineDateTime, LocalDateTime openDateTime){
    LocalDateTime now = LocalDateTime.now();

    if(now.isBefore(openDateTime)){
      return "WAIT";
    } else if(now.isAfter(deadlineDateTime)){
      return "CLOSE";
    } else {
      return "OPEN";
    }
  }
}
