package com.ongi.ongi_back.common.dto.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderItemRequestDto {
  @NotBlank()
  private String paymentKey;
  
  private Integer productSequence;
  
  private Integer quantity;
  
  private String deliveryAddressSnapshot;

  private String userId;
}
