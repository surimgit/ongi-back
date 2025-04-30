package com.ongi.ongi_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.calendar.PatchCalendarRequestDto;
import com.ongi.ongi_back.common.dto.request.calendar.PostScheduleRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetAllScheduleResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.PostScheduleResponseDto;
import com.ongi.ongi_back.service.CalendarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public ResponseEntity<? super GetAllScheduleResponseDto> getAllSchedule(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetAllScheduleResponseDto> response = calendarService.getAllSchedule(userId);
        return response;
    }

    @PostMapping
    public ResponseEntity<? super PostScheduleResponseDto> postSchedule(
        @RequestBody @Valid PostScheduleRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostScheduleResponseDto> response = calendarService.postSchedule(requestBody, userId);
        return response;
    }

    @PatchMapping("/{calendarSequence}")
    public ResponseEntity<ResponseDto> patchSchedule(
            @RequestBody PatchCalendarRequestDto requestBody,
            @PathVariable("calendarSequence") Integer calendarSequence,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = calendarService.patchSchedule(requestBody, calendarSequence, userId);
        return response;
    }

    @DeleteMapping("/{calendarSequence}")
    public ResponseEntity<ResponseDto> deleteSchedule(
            @PathVariable Integer calendarSequence,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = calendarService.deleteSchedule(calendarSequence);
        return response;
    }
}
