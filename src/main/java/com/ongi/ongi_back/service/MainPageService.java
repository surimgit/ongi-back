package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;

public interface MainPageService {
    ResponseEntity<? super GetHelperPostListResponseDto> comparisonTag(String userId); 
}
