package com.ongi.ongi_back.common.dto.request.shoppingCart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatchShoppingCartRequestDto {
  
  @NotNull
  private Integer shoppingCartSequence;
  
  @NotNull
  private Integer quantity;
}
