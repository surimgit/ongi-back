package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ongi.ongi_back.common.entity.YouthCenterEntity;
import com.ongi.ongi_back.common.util.enums.RegionCdEnum;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouthCenterListVO {
    private String plcyNo; // 정책번호
    private String plcyNm; // 정책명
    private String plcyExplnCn;  // 정책소개
    private String lclsfNm;    // 카테고리(대분류) 일자리/주거/교육/복지문화
    private String zipCd;    // 지역 
    private String aplyPrdSeCd; //상시/한정 0057002 / 0057001
    private String aplyYmd; // 신청기간

    private YouthCenterListVO (YouthCenterEntity policies) {
        this.plcyNo = policies.getPlcyNo();
        this.plcyNm = policies.getPlcyNm();
        this.plcyExplnCn = policies.getPlcyExplnCn();
        this.lclsfNm = policies.getLclsfNm();
        this.zipCd = RegionCdEnum.getNameFromZipCd(policies.getZipCd());
        this.aplyPrdSeCd = policies.getAplyPrdSeCd();
        this.aplyYmd = policies.getAplyYmd();
    }

    public static List<YouthCenterListVO> getList(List<YouthCenterEntity> policies){
        List<YouthCenterListVO> list = new ArrayList<>();
        for(YouthCenterEntity youthCenterEntity: policies){
            YouthCenterListVO vo = new YouthCenterListVO(youthCenterEntity);
            list.add(vo);
        }
        return list;
    }
}
