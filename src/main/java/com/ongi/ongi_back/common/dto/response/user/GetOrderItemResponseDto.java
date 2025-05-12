package com.ongi.ongi_back.common.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.vo.OrderItemVO;

import lombok.Getter;

@Getter
public class GetOrderItemResponseDto extends ResponseDto {
  private List<OrderItemVO> orderItems;

  private GetOrderItemResponseDto(List<OrderItemVO> orderItems){
    this.orderItems = orderItems;
  }

  public static ResponseEntity<GetOrderItemResponseDto> success(List<OrderItemVO> orderItems) {
    GetOrderItemResponseDto body = new GetOrderItemResponseDto(orderItems);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
