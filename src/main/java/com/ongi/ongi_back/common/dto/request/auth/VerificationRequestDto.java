package com.ongi.ongi_back.common.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequestDto {
    private String telNumber;
    private String code;
}
