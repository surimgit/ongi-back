package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.ReportEntity;

import lombok.Getter;

@Getter
public class ReportVO {
    private String reportedId;
    private String reporterId;
    private Integer reportedEntityNum;
    private String reportedEntityType;
    private String reportedDate;
    private String reportCategory;
    private String reportDetail;
    private String reportContent;
    private String reportProcess;

    private ReportVO(ReportEntity reportEntity) {
        this.reportedId = reportEntity.getReportedId();
        this.reporterId = reportEntity.getReporterId();
        this.reportedEntityNum = reportEntity.getReportedEntityNum();
        this.reportedEntityType = reportEntity.getReportedEntityType();
        this.reportedDate = reportEntity.getReportedDate();
        this.reportCategory = reportEntity.getReportCategory();
        this.reportDetail = reportEntity.getReportDetail();
        this.reportContent = reportEntity.getReportedContent();
        if(reportEntity.getReportProcess() == null) this.reportProcess = "미처리";
        else this.reportProcess = reportEntity.getReportProcess();
    }

    public static List<ReportVO> getList(List<ReportEntity> reportEntities) {
        List<ReportVO> list = new ArrayList<>();

        for (ReportEntity reportEntity: reportEntities) {
            ReportVO vo = new ReportVO(reportEntity);
            list.add(vo);
        }

        return list;
    }
}
