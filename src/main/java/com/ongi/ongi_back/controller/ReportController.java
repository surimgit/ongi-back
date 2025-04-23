package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.service.ReportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping({"", "/"})
    public ResponseEntity<ResponseDto> postReport(
        @RequestBody @Valid PostReportRequestDto dto,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = reportService.postReport(dto, userId);
        return response;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<? super GetReportResponseDto> getReport(
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetReportResponseDto> response = reportService.getReport(userId);
        return response;
    }
    
}
