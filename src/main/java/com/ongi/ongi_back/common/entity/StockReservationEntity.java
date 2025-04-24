package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.group.PostStockReservationRequestDto;
import com.ongi.ongi_back.common.vo.StockReservationVO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "stock_reservation")
@Entity(name = "stock_reservation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockReservationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String userId;
  private Integer productSequence;
  private Integer quantity;
  private String expiredAt;

  public StockReservationEntity(StockReservationVO vo, String userId){
    LocalDateTime expiredTime = LocalDateTime.now().plusMinutes(5);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.userId = userId;
    this.productSequence = vo.getProductSequence();
    this.expiredAt = expiredTime.format(dateTimeFormatter);
    this.quantity = vo.getQuantity();
  }
}
