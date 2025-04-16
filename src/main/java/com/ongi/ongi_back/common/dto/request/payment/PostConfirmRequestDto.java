package com.ongi.ongi_back.common.dto.request.payment;

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
public class PostConfirmRequestDto {

  @NotBlank
  private String paymentKey;

  @NotBlank
  private String orderId;
  
  @NotNull
  private Integer amount;

  @NotBlank
  @Pattern(regexp = "^READY|IN_PROGRESS|WAITING_FOR_DEPOSIT|DONE|CANCELED|PARTIAL_CANCELED|ABORTED|EXPIRED$")
  private String status = "READY";

}
