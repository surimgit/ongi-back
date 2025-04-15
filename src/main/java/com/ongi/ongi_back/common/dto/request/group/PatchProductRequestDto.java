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
public class PatchProductRequestDto {
  
  @NotBlank
  private String name;

  @NotNull
  private Integer price;

  @NotBlank
  @Pattern(regexp = "^건강식품|패션의류|스포츠|가전제품|식품|뷰티|기타$")
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

