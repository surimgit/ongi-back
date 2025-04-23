package com.ongi.ongi_back.common.dto.request.calendar;

import java.time.LocalDateTime;

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
public class PostScheduleRequestDto {
    @NotBlank
    private String calendarTitle;

    private String calendarCategory;
    private String calendarMemo;

    @NotNull
    private LocalDateTime calendarStart;
    @NotNull
    private LocalDateTime calendarEnd;
    @NotNull
    @Pattern(regexp="^(반복 없음|매일|매주|매년)$")
    private String calendarRepeat;

    @Pattern(regexp="^pink|red|blue|yellow|purple|green|orange$")
    private String color;
}