package com.ongi.ongi_back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.entity.ReportEntity;
import com.ongi.ongi_back.repository.ReportRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImplement implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postReport(PostReportRequestDto dto, String userId) {
        
        try {

            ReportEntity reportEntity = new ReportEntity(dto, userId);
            reportRepository.save(reportEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super GetReportResponseDto> getReport(String userId) {

        List<ReportEntity> reportEntities = null;
        
        try {

            reportEntities = reportRepository.findByReportProcessIsNullOrderByReportSequenceDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReportResponseDto.success(reportEntities);
    }
    
}
