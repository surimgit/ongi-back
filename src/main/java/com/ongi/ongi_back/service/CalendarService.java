package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.calendar.PatchCalendarRequestDto;
import com.ongi.ongi_back.common.dto.request.calendar.PostScheduleRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetAllScheduleResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.PostScheduleResponseDto;

public interface CalendarService {
    ResponseEntity<? super GetAllScheduleResponseDto> getAllSchedule(String userId);
    ResponseEntity<? super PostScheduleResponseDto> postSchedule(PostScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchSchedule(PatchCalendarRequestDto dto, Integer calendarSequence, String userId);
    ResponseEntity<ResponseDto> deleteSchedule(Integer calendarSequence);
}
