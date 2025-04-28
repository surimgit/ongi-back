package com.ongi.ongi_back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.report.PatchReportProcessRequestDto;
import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetAlertedCountResponseDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportResponseDto;
import com.ongi.ongi_back.common.dto.response.report.GetReportsResponseDto;
import com.ongi.ongi_back.common.entity.ReportEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
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
            UserEntity reportedUserEntity = userRepository.findByUserId(dto.getReportedId());
            if (reportedUserEntity.getIsResigned()) return ResponseDto.alreadyResigned();
            reportRepository.save(reportEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super GetReportsResponseDto> getReports(String userId) {

        List<ReportEntity> reportEntities = null;
        
        try {

            reportEntities = reportRepository.findByReportProcessIsNullOrderByReportSequenceDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReportsResponseDto.success(reportEntities);
    }

    @Override
    public ResponseEntity<? super GetReportsResponseDto> getProcessedReports(String userId) {

        List<ReportEntity> reportEntities = null;
        
        try {

            reportEntities = reportRepository.findByReportProcessIsNotNullOrderByReportSequenceDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReportsResponseDto.success(reportEntities);
    }

    @Override
    public ResponseEntity<? super GetReportResponseDto> getReport(Integer reportSequence) {
        
        ReportEntity reportEntity = null;

        try { 

            reportEntity = reportRepository.findByReportSequence(reportSequence);
            if (reportEntity == null) return ResponseDto.noExistReport();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReportResponseDto.success(reportEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> patchReportProcess(PatchReportProcessRequestDto dto, String userId, Integer reportSequence) {
        
        ReportEntity reportEntity = null;

        try {

            reportEntity = reportRepository.findByReportSequence(reportSequence);
            if (reportEntity == null) return ResponseDto.noExistReport();
            
            reportEntity.setReportProcess(dto.getProcess());
            reportRepository.save(reportEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetAlertedCountResponseDto> getAlertedCount(String userId) {

        Integer alertedCount = null;
        
        try {

            alertedCount = reportRepository.countAllByReportedIdAndReportProcess(userId, "경고");
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetAlertedCountResponseDto.success(alertedCount);
    }
    
}
