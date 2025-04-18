package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.alert.PostAlertRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.alert.GetAlertResponseDto;

public interface AlertService {
    ResponseEntity<ResponseDto> postAlert(PostAlertRequestDto dto);
    ResponseEntity<? super GetAlertResponseDto> getAlert(String userId);
}
