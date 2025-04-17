package com.ongi.ongi_back.common.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserRequestDto {
  private String userPassword;

  private String address;

  private String detailAddress;
}
