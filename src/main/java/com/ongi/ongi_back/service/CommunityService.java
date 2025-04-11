package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.PostCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface CommunityService {
    ResponseEntity<ResponseDto> postInfo(PostCommunityPostRequestDto dto, String userId);
}
