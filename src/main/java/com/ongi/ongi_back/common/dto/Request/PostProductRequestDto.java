package com.ongi.ongi_back.common.dto.Request;

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
public class PostProductRequestDto {
  
  @NotBlank
  private String name;

  @NotNull
  private Integer price;

  @NotBlank
  private String category;

  @NotBlank
  private String content;

  @NotNull
  private Integer productQuantity;

  @NotNull
  private Integer boughtAmount;

  @NotBlank
  private String deadline;

  @NotNull
  private Boolean isSoldOut;

  @NotNull
  private Integer purchasedPeople;

  private Integer adPayment;
  private String reserveDate;

}
