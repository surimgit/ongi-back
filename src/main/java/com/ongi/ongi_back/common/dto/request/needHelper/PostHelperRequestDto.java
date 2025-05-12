package com.ongi.ongi_back.common.dto.request.needHelper;

import java.time.LocalDateTime;

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
public class PostHelperRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String meetingType;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String schedule;
    @NotBlank
    private String reward;
    @NotNull
    private LocalDateTime date;
    @NotBlank
    private String keyword;
}
