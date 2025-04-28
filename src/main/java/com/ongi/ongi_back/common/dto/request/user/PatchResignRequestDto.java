package com.ongi.ongi_back.common.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchResignRequestDto {
    private String userId;
    private Boolean isAdmin;
    private String reason;
}
