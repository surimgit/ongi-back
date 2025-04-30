package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyListResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyViewResponseDto;

public interface YouthCenterService {
    ResponseEntity<? super GetPolicyListResponseDto> getPolicyList(String keyword);
    ResponseEntity<? super GetPolicyViewResponseDto> getPolicyView(String plcyNm, String plcyNo);
}
