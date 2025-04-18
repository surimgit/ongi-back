package com.ongi.ongi_back.common.dto.response.user;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto{
  private String userId;
  private String nickname;
  private String profileImage;
}
