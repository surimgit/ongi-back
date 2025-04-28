package com.ongi.ongi_back.common.dto.response.report;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ReportEntity;

import lombok.Getter;

@Getter
public class GetReportResponseDto extends ResponseDto {
    private String reporterId;
    private String reportedId;
    private Integer entityNum;
    private String entityType;
    private String reportedContent;
    private String date;
    private String category;
    private String detail;
    private String process;

    private GetReportResponseDto(ReportEntity reportEntity) {
        this.reportedId = reportEntity.getReportedId();
        this.reporterId = reportEntity.getReporterId();
        this.entityNum = reportEntity.getReportedEntityNum();
        this.entityType = reportEntity.getReportedEntityType();
        this.reportedContent = reportEntity.getReportedContent();
        this.date = reportEntity.getReportedDate();
        this.category = reportEntity.getReportCategory();
        this.detail = reportEntity.getReportDetail();
        this.process = reportEntity.getReportProcess();
    }

    public static ResponseEntity<GetReportResponseDto> success(ReportEntity reportEntity) {
        GetReportResponseDto body = new GetReportResponseDto(reportEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
