package com.ongi.ongi_back.common.dto.request.shoppingCart;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostShoppingCartRequestDto {

  @NotNull
  private Integer productSequence;

  @NotNull
  private Integer quantity;
  
}
