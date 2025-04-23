package com.ongi.ongi_back.common.dto.response.report;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ReportEntity;
import com.ongi.ongi_back.common.vo.ReportVO;

import lombok.Getter;

@Getter
public class GetReportResponseDto extends ResponseDto {
    private List<ReportVO> reports;

    private GetReportResponseDto(List<ReportEntity> responseEntities) {
        this.reports = ReportVO.getList(responseEntities);
    }
    
    public static ResponseEntity<GetReportResponseDto> success(List<ReportEntity> reportEntities) {
        GetReportResponseDto body = new GetReportResponseDto(reportEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
