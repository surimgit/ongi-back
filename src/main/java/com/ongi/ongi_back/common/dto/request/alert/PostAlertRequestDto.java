package com.ongi.ongi_back.common.dto.request.alert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAlertRequestDto {
    @NotBlank
    private String alertType;
    @NotBlank
    private String senderId = "admin12345";
    @NotBlank
    private String receiverId;
    @NotNull
    private Integer alertEntitySequence;
    private String reason;
}
