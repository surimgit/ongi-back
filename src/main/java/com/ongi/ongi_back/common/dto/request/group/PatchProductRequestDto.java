package com.ongi.ongi_back.common.dto.Request.group;

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
public class PatchProductRequestDto {
  
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
  private String image;
}

