package com.ongi.ongi_back.common.dto.request.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
  @Pattern(regexp = "^식품|의류|생필품|가전제품|기타$")
  private String category;

  @NotBlank
  private String content;

  @NotNull
  private Integer productQuantity;

  @NotBlank
  private String deadline;

  @NotNull
  private Integer boughtAmount = 0;

  @NotNull
  private Boolean isSoldOut = false;

  @NotNull
  private Integer purchasedPeople = 0;

  @NotNull
  private String image;

  private Integer adPayment;
  private String openDate;
}
