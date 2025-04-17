package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.community.PatchCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommunityRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityLikedResponseDto;

public interface CommunityService {
    ResponseEntity<ResponseDto> postCommunityPost(PostCommunityRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchCommunityPost(PatchCommunityPostRequestDto dto, Integer postSequence, String userId);
    ResponseEntity<ResponseDto> patchCommunityViewCount(Integer postSequence);
    ResponseEntity<ResponseDto> deleteCommunityPost(Integer diaryNumber, String userId);
    
    ResponseEntity<? super GetCommunityPostResponseDto> getCommunityPost(Integer postSequence);
    ResponseEntity<? super GetCommunityResponseDto> getCommunity();

    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByWriter(String keyword);
    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByTitle(String keyword);
    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByContent(String keyword);

    ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer postSequence, String userId);
    ResponseEntity<ResponseDto> deleteCommunityComment(Integer postSequence, Integer commentSequence, String userId);
    ResponseEntity<? super GetCommunityCommentResponseDto> getCommunityComment(Integer postSequence);

    ResponseEntity<ResponseDto> putCommunityLiked(Integer postSequence, String userId);
    ResponseEntity<? super GetCommunityLikedResponseDto> getCommunityLiked(Integer postSequence);
}
