package com.ongi.ongi_back.common.dto.request.group;

import java.util.List;

import com.ongi.ongi_back.common.vo.StockReservationVO;

import lombok.Getter;

@Getter
public class PostStockReservationRequestDto {
  
  List<StockReservationVO> list;
}
