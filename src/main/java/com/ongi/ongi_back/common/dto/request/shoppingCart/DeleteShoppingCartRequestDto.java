package com.ongi.ongi_back.common.dto.request.shoppingCart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteShoppingCartRequestDto {
  @NotNull
  private Integer shoppingCartSequence;
}
