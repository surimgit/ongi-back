package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.report.PostReportRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="report")
@Table(name="report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer reportSequence;
    private String reporterId;
    private String reportedId;
    private Integer reportedEntityNum;
    private String reportedEntityType;
    private String reportedContent;
    private String reportedDate;
    private String reportCategory;
    private String reportDetail;
    private String reportProcess;

    public ReportEntity(PostReportRequestDto dto, String userId) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.reporterId = userId;
        this.reportedId = dto.getReportedId();
        this.reportedEntityNum = dto.getReportedEntityNum();
        this.reportedEntityType = dto.getReportedEntityType();
        this.reportedContent = dto.getReportedContent();
        this.reportedDate = now.format(dateTimeFormatter);
        this.reportCategory = dto.getReportCategory();
        this.reportDetail = dto.getReportDetail();
    }
}
