package com.ongi.ongi_back.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "order_item")
@Entity(name = "order_item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderItemSequence;
  private String paymentKey;
  private Integer productSequence;
  private String productName;
  private Integer price;
  private Integer quantity;
  private Integer totalPrice;
  private String image;

  public OrderItemEntity(ProductEntity productEntity, Integer quantity, String paymentKey){
    this.paymentKey = paymentKey;
    this.productSequence = productEntity.getSequence();
    this.productName = productEntity.getName();
    this.price = productEntity.getPrice();
    this.quantity = quantity;
    this.totalPrice = price * quantity;
    this.image = productEntity.getImage();
  }
}
