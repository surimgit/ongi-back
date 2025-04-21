package com.ongi.ongi_back.common.entity;

import org.springframework.data.annotation.Transient;

import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "shopping_cart")
@Table(name = "shopping_cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer shoppingCartSequence;

  private String userId;
  private Integer productSequence;
  private Integer quantity;

  public ShoppingCartEntity(PostShoppingCartRequestDto dto, String userId) {
    this.userId = userId;
    this.productSequence = dto.getProductSequence();
    this.quantity = dto.getQuantity();
  }

  public void patch(Integer quantity){
    this.quantity = quantity;
  }

  public void patch(PatchShoppingCartRequestDto dto){
    this.quantity = dto.getQuantity();
  }

}
