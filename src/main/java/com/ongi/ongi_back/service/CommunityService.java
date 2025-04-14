package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostInfoPostRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;

public interface CommunityService {
    ResponseEntity<ResponseDto> postInfo(PostInfoPostRequestDto dto, String userId);
    ResponseEntity<ResponseDto> deleteInfo(Integer diaryNumber, String userId);
    
    ResponseEntity<? super GetCommunityPostResponseDto> getCommunityPost(Integer postSequence);
    ResponseEntity<? super GetCommunityResponseDto> getCommunity();

    ResponseEntity<? super GetCommunityResponseDto> getCommunityByWriter(String nickname);

    ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer postSequence, String userId);
}
