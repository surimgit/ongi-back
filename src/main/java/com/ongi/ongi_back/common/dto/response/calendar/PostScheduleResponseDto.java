package com.ongi.ongi_back.common.dto.response.calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class PostScheduleResponseDto extends ResponseDto{
    private Integer calendarSequence;

    private PostScheduleResponseDto(Integer calendarSequence){
        this.calendarSequence = calendarSequence;
    }

    public static ResponseEntity<PostScheduleResponseDto> success(Integer calendarSequence) {
        PostScheduleResponseDto body = new PostScheduleResponseDto(calendarSequence);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public Integer getCalendarSequence() {
        return calendarSequence;
    }
}
