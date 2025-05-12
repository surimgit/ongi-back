package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.service.MainPageService;
import com.ongi.ongi_back.service.NeedHelperService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainPageController {

    private final NeedHelperService needHelperService;
    private final MainPageService mainPageService;

    @GetMapping({"/need-helper"})
    public ResponseEntity<? super GetHelperPostListResponseDto> getHelperPostList() {
        ResponseEntity<? super GetHelperPostListResponseDto> response = needHelperService.getHelperPostList();
        return response;
    }

    @GetMapping("/need-helper/{userId}")
    public ResponseEntity<? super GetHelperPostListResponseDto> comparisonTag(
        @PathVariable String userId) {
        return mainPageService.comparisonTag(userId);
    }

}
