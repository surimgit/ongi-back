package com.ongi.ongi_back.common.vo;

import com.ongi.ongi_back.common.entity.StockReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockReservationVO {
  Integer productSequence;
  Integer quantity;

  private StockReservationVO(StockReservationEntity entity){
    this.productSequence = entity.getProductSequence();
    this.quantity = entity.getProductSequence();
  }
}
