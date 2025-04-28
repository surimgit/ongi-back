package com.ongi.ongi_back.common.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindIdRequestDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String telNumber;
}
