package com.ongi.ongi_back.common.dto.response.shoppingCart;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.vo.ShoppingCartVO;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CartEntity;

import lombok.Getter;

@Getter
public class GetShoppingCartResponseDto extends ResponseDto {
  private List<ShoppingCartVO> shoppingCarts;

  private GetShoppingCartResponseDto(List<CartEntity> cartEntities){
    this.shoppingCarts = ShoppingCartVO.getList(cartEntities);
  }

  public static ResponseEntity<GetShoppingCartResponseDto> success(List<CartEntity> cartEntities){
    GetShoppingCartResponseDto body = new GetShoppingCartResponseDto(cartEntities);

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
