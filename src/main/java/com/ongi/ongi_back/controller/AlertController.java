package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.alert.PostAlertRequestDto;
import com.ongi.ongi_back.common.dto.response.alert.GetAlertResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.service.AlertService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/alert")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping({"/", ""})
    public ResponseEntity<ResponseDto> postAlert(
        @RequestBody @Valid PostAlertRequestDto dto,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = alertService.postAlert(dto);
        return response;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<? super GetAlertResponseDto> getAlert(
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetAlertResponseDto> response = alertService.getAlert(userId);
        return response;
    }

    
}
