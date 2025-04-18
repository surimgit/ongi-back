package com.ongi.ongi_back.common.dto.response.shoppingCart;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.vo.ShoppingCartVO;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ShoppingCartEntity;

import lombok.Getter;

@Getter
public class GetShoppingCartResponseDto extends ResponseDto {
  private List<ShoppingCartVO> shoppingCarts;

  private GetShoppingCartResponseDto(List<ShoppingCartVO> shoppingCarts){
    this.shoppingCarts = shoppingCarts;
  }

  public static ResponseEntity<GetShoppingCartResponseDto> success(List<ShoppingCartVO> shoppingCarts){
    GetShoppingCartResponseDto body = new GetShoppingCartResponseDto(shoppingCarts);

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
