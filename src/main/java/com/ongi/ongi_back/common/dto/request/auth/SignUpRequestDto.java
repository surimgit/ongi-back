package com.ongi.ongi_back.common.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

  @NotBlank
  @Pattern(regexp="^[a-zA-Z0-9]{6,20}$")
  private String userId;

  @NotBlank
  @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$")
  private String userPassword;

  @NotBlank
  @Pattern(regexp="^[가-힣]{2,5}$")
  private String name;

  @NotBlank
  @Pattern(regexp="^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$")
  private String telNumber;

  @NotBlank
  @Pattern(regexp="^(남|여)$")
  private String gender;

}
