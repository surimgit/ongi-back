package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.report.PatchReportProcessRequestDto;
import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportsResponseDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetAlertedCountResponseDto;
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
    public ResponseEntity<? super GetReportsResponseDto> getReports(
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetReportsResponseDto> response = reportService.getReports(userId);
        return response;
    }
    
    @GetMapping({"/processed"})
    public ResponseEntity<? super GetReportsResponseDto> getProcessedReports(
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetReportsResponseDto> response = reportService.getProcessedReports(userId);
        return response;
    }

    @GetMapping("/{reportSequence}")
    public ResponseEntity<? super GetReportResponseDto> getReport(
        @PathVariable("reportSequence") Integer reportSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetReportResponseDto> response = reportService.getReport(reportSequence);
        return response;
    }

    @PatchMapping("/{reportSequence}")
    public ResponseEntity<ResponseDto> patchReportProcess(
        @RequestBody @Valid PatchReportProcessRequestDto dto,
        @PathVariable("reportSequence") Integer reportSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = reportService.patchReportProcess(dto, userId, reportSequence);
        return response;
    }

    @GetMapping("/alerted-count")
    public ResponseEntity<? super GetAlertedCountResponseDto> getAlertedCount(
        @RequestParam(value="reported-id", required=false) String reportedId,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<? super GetAlertedCountResponseDto> response = reportService.getAlertedCount(reportedId);
        return response;
    }
    
}
