package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.report.PatchReportProcessRequestDto;
import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportsResponseDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetAlertedCountResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface ReportService {
    ResponseEntity<ResponseDto> postReport(PostReportRequestDto dto, String userId);
    ResponseEntity<? super GetReportsResponseDto> getReports(String userId);
    ResponseEntity<? super GetReportsResponseDto> getProcessedReports(String userId);
    ResponseEntity<? super GetReportResponseDto> getReport(Integer reportSequence);
    ResponseEntity<ResponseDto> patchReportProcess(PatchReportProcessRequestDto dto, String userId, Integer reportSequence);
    ResponseEntity<? super GetAlertedCountResponseDto> getAlertedCount(String userId);
}
