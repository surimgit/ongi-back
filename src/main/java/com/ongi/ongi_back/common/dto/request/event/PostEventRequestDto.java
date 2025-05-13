package com.ongi.ongi_back.common.dto.request.event;

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
public class PostEventRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String deadline;
    @NotNull
    private Integer neededPoint;
    @NotBlank
    private String content;
    private String image;
}
