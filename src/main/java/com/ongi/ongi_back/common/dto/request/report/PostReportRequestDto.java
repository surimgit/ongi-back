package com.ongi.ongi_back.common.dto.request.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReportRequestDto {
    private String reportedId;
    private Integer reportedEntityNum;
    private String reportedEntityType;
    private String reportedContent;
    private String reportCategory;
    private String reportDetail;
}
