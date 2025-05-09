package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.YouthCenterEntity;
import com.ongi.ongi_back.common.util.enums.JobCdEnum;
import com.ongi.ongi_back.common.util.enums.PlcyMajorCdEnum;
import com.ongi.ongi_back.common.util.enums.RegionCdEnum;
import com.ongi.ongi_back.common.util.enums.SBizCdEnum;
import com.ongi.ongi_back.common.util.enums.SchoolCdEnum;

import lombok.Getter;

@Getter
public class YouthCenterViewVO {
    private String plcyNm; // 정책명
    private String plcyExplnCn;  // 정책소개
    private String rgtrInstCdNm;

    //! 한 눈에 보는 정책 요약
    private String plcyNo; // 정책번호
    private String lclsfNm;    // 카테고리(대분류) 일자리/주거/교육/복지문화
    private String plcySprtCn; // 정책지원내용
    private String bizPrdBgngYmd; // 사업기간시작일자
    private String bizPrdEndYmd; // 사업기간종료일자
    private String aplyYmd; // 신청기간
    private String aplyPrdSeCd;
    //신청기간여부 (0057001: 한정, 0057002: 상시, 0057003: 기타)
    private String sprtSclCnt; // 지원규모수

    //! 신청방법
    private String sprtTrgtMinAge; // 지원대상최소연령
    private String sprtTrgtMaxAge; // 지원대상최대연령
    private String sprtTrgtAgeLmtYn; // 지원대상연령제한여부 (Y: 제한없음, N: 제한있음)
    private String zipCd;    // 지역 
    private String earnCndSeCd; 
    // 소득조건구분코드 (0043001: 무관, 0043002: 제한있음, 0043003: 기타)
    private String earnMinAmt; // 소득최소금액
    private String earnMaxAmt; // 소득최대금액
    private String earnEtxCn; // 소득기타내용
    private String schoolCd; // 정책학력요건코드 (0049001~0049010)
    private String plcyMajorCd; // 정책전공요건코드 (0011001~0011009)
    private String jobCd; // 정책취업요건코드 (0013001~0013010)
    private String sBizCd; // 정책특화요건코드 (0014001~0014010)
    private String addAplyQlfcCndCn; // 추가신청자격조건내용
    private String ptcpPrpTrgtCn; // 참여제한대상내용
    
    //! 신청절차
    private String plcyAplyMthdCn; // 정책신청방법내용
    private String srngMthdCn; // 심사방법내용
    private String aplyUrlAddr; // 신청URL주소
    private String sbmsnDcmntCn; // 제출서류내용
    
    private YouthCenterViewVO (YouthCenterEntity policies) {
        this.plcyNm = policies.getPlcyNm();
        this.plcyExplnCn = policies.getPlcyExplnCn();
        this.lclsfNm = policies.getLclsfNm();
        this.zipCd = RegionCdEnum.getNameFromZipCd(policies.getZipCd());
        this.aplyPrdSeCd = policies.getAplyPrdSeCd();
        this.aplyYmd = policies.getAplyYmd();
        this.plcyNo = policies.getPlcyNo();
        this.plcySprtCn = policies.getPlcySprtCn();
        this.bizPrdBgngYmd = policies.getBizPrdBgngYmd();
        this.bizPrdEndYmd = policies.getBizPrdEndYmd();
        this.sprtSclCnt = policies.getSprtSclCnt();
        this.sprtTrgtMinAge = policies.getSprtTrgtMinAge();
        this.sprtTrgtMaxAge = policies.getSprtTrgtMaxAge();
        this.sprtTrgtAgeLmtYn = policies.getSprtTrgtAgeLmtYn();
        this.earnCndSeCd = policies.getEarnCndSeCd();
        this.earnMinAmt = policies.getEarnMinAmt();
        this.earnMaxAmt = policies.getEarnMaxAmt();
        this.earnEtxCn = policies.getEarnEtxCn();
        this.schoolCd = SchoolCdEnum.getDescription(policies.getSchoolCd());
        this.plcyMajorCd = PlcyMajorCdEnum.getDescription(policies.getPlcyMajorCd());
        this.jobCd = JobCdEnum.getDescription(policies.getJobCd());
        this.sBizCd = SBizCdEnum.getDescription(policies.getSBizCd());
        this.addAplyQlfcCndCn = policies.getAddAplyQlfcCndCn();
        this.ptcpPrpTrgtCn = policies.getPtcpPrpTrgtCn();
        this.plcyAplyMthdCn = policies.getPlcyAplyMthdCn();
        this.srngMthdCn = policies.getSrngMthdCn();
        this.aplyUrlAddr = policies.getAplyUrlAddr();
        this.sbmsnDcmntCn = policies.getSbmsnDcmntCn();
    }

    public static List<YouthCenterViewVO> getList(List<YouthCenterEntity> policies){
        List<YouthCenterViewVO> list = new ArrayList<>();
        for(YouthCenterEntity youthCenterEntity: policies){
            YouthCenterViewVO vo = new YouthCenterViewVO(youthCenterEntity);
            list.add(vo);
        }
        return list;
    }
}
