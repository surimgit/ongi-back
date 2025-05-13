package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.event.PostEventApplyRequestDto;
import com.ongi.ongi_back.common.dto.request.event.PostEventRequestDto;
import com.ongi.ongi_back.common.dto.response.event.GetEventListResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping({"/", ""})
    public ResponseEntity<ResponseDto> postEvent(
        @RequestBody @Valid PostEventRequestDto requestBody,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = eventService.postEvent(requestBody);
        return response;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<? super GetEventListResponseDto> getEventList() {
        ResponseEntity<? super GetEventListResponseDto> response = eventService.getEventList();
        return response;
    }

    @PostMapping({"/apply"})
    public ResponseEntity<ResponseDto> postApply(
        @RequestBody @Valid PostEventApplyRequestDto requestBody,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = eventService.postEventApply(requestBody, userId);
        return response;
    }
    
}
