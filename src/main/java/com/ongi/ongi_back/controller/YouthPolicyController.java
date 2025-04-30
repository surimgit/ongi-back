package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyListResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyViewResponseDto;
import com.ongi.ongi_back.service.YouthCenterService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class YouthPolicyController {

    private final YouthCenterService youthCenterService;

    public YouthPolicyController(YouthCenterService youthCenterService) {
        this.youthCenterService = youthCenterService;
    }

    @GetMapping("/policy-list")
    ResponseEntity<? super GetPolicyListResponseDto> getPolicyList(
        @RequestParam(name = "keyword", required = false) String keyword
    ) {
        ResponseEntity<? super GetPolicyListResponseDto> response = youthCenterService.getPolicyList(keyword);
        return response;
    }

    @GetMapping("/policy-view")
    ResponseEntity<? super GetPolicyViewResponseDto> getPolicyView(
        @RequestParam(name= "plcyNm") String plcyNm,
        @RequestParam(name= "plcyNo") String plcyNo
    ) {
        ResponseEntity<? super GetPolicyViewResponseDto> response = youthCenterService.getPolicyView(plcyNm, plcyNo);
        return response;
    }

}
