package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.community.PatchCommunityCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PatchCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommunityRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityLikedResponseDto;

public interface CommunityService {
    // 게시글 작성, 수정, 조회수 증가, 삭제
    ResponseEntity<ResponseDto> postCommunityPost(PostCommunityRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchCommunityPost(PatchCommunityPostRequestDto dto, Integer postSequence, String userId);
    ResponseEntity<ResponseDto> patchCommunityViewCount(Integer postSequence);
    ResponseEntity<ResponseDto> deleteCommunityPost(Integer diaryNumber, String userId);
    
    // 게시글 상세 보기, 게시글 리스트
    ResponseEntity<? super GetCommunityPostResponseDto> getCommunityPost(Integer postSequence);
    ResponseEntity<? super GetCommunityResponseDto> getCommunity();
    ResponseEntity<? super GetCommunityResponseDto> getBoard(String board);
    ResponseEntity<? super GetCommunityResponseDto> getCategory(String category);

    // 게시글 검색 기능 (작성자, 제목, 내용)
    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByWriter(String keyword);
    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByTitle(String keyword);
    ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByContent(String keyword);

    // 댓글 작성, 삭제, 리스트, 갯수
    ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer postSequence, String userId);
    ResponseEntity<ResponseDto> deleteCommunityComment(Integer postSequence, Integer commentSequence, String userId);
    ResponseEntity<? super GetCommunityCommentResponseDto> getCommunityComment(Integer postSequence);
    ResponseEntity<ResponseDto> patchCommunityComment(PatchCommunityCommentRequestDto dto, Integer postSequence, Integer commentSequence, String userId);

    // 좋아요 누르기, 누른 명단 불러오기
    ResponseEntity<ResponseDto> putCommunityLiked(Integer postSequence, String userId);
    ResponseEntity<? super GetCommunityLikedResponseDto> getCommunityLiked(Integer postSequence);
}
