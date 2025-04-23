package com.ongi.ongi_back.common.dto.request.group;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchProductQuantityRequestDto {
  @NotNull
  private Integer productSequence;
  @NotNull
  private Integer quantity;
}
