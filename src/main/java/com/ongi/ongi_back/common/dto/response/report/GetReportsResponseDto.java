package com.ongi.ongi_back.common.dto.response.report;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ReportEntity;
import com.ongi.ongi_back.common.vo.ReportVO;

import lombok.Getter;

@Getter
public class GetReportsResponseDto extends ResponseDto {
    private List<ReportVO> reports;

    private GetReportsResponseDto(List<ReportEntity> responseEntities) {
        this.reports = ReportVO.getList(responseEntities);
    }
    
    public static ResponseEntity<GetReportsResponseDto> success(List<ReportEntity> reportEntities) {
        GetReportsResponseDto body = new GetReportsResponseDto(reportEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
