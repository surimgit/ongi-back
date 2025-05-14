package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.event.PostEventApplyRequestDto;
import com.ongi.ongi_back.common.dto.request.event.PostEventRequestDto;
import com.ongi.ongi_back.common.dto.response.event.GetEventListResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface EventService {
    ResponseEntity<ResponseDto> postEvent(PostEventRequestDto dto, String userId);
    ResponseEntity<? super GetEventListResponseDto> getEventList();
    void closeExpiredEvents();
    ResponseEntity<ResponseDto> postEventApply(PostEventApplyRequestDto dto, String userId);
}
