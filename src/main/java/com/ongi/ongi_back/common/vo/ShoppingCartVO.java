package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.CartEntity;

import lombok.Getter;

@Getter
public class ShoppingCartVO {
  private Integer shoppingCartSequence;
  private Integer productSequence;
  private Integer quantity;
  private String addedDate;

  private ShoppingCartVO(CartEntity cartEntity){
    this.shoppingCartSequence = cartEntity.getShoppingCartSequence();
    this.productSequence = cartEntity.getProductSequence();
    this.quantity = cartEntity.getQuantity();
    this.addedDate = cartEntity.getAdded_date();
  }

  public static List<ShoppingCartVO> getList(List<CartEntity> cartEntities){
    List<ShoppingCartVO> list = new ArrayList<>();
    for(CartEntity cartEntity: cartEntities){
      ShoppingCartVO vo = new ShoppingCartVO(cartEntity);
      list.add(vo);
    }

    return list;
  }

}
