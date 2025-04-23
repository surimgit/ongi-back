package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface ReportService {
    ResponseEntity<ResponseDto> postReport(PostReportRequestDto dto, String userId);
    ResponseEntity<? super GetReportResponseDto> getReport(String userId);
}
