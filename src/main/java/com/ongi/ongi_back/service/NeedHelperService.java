package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.needHelper.PatchHelperPostRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperLikedResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto;

public interface NeedHelperService {
    ResponseEntity<ResponseDto> postHelper(PostHelperRequestDto dto, String userId);
    ResponseEntity<? super GetHelperPostListResponseDto> getHelperPostList();
    ResponseEntity<? super GetHelperPostResponseDto> getHelperPost(Integer sequence);
    ResponseEntity<ResponseDto> patchHelperPost(PatchHelperPostRequestDto dto, Integer sequence, String userId);
    ResponseEntity<ResponseDto> deleteHelperPost(Integer sequence, String userId);

    // 댓글 작성, 삭제, 리스트, 갯수
    ResponseEntity<ResponseDto> postComment(PostHelperCommentRequestDto dto, Integer postSequence, String userId);
    ResponseEntity<ResponseDto> deleteHelperComment(Integer postSequence, Integer commentSequence, String userId);
    ResponseEntity<? super GetHelperCommentsResponseDto> getHelperComments(Integer postSequence);
    ResponseEntity<? super GetHelperCommentResponseDto> getHelperComment(Integer postSequence, Integer commentSequence);

    // 좋아요 누르기, 누른 명단 불러오기
    ResponseEntity<ResponseDto> putHelperLiked(Integer postSequence, String userId);
    ResponseEntity<? super GetHelperLikedResponseDto> getHelperLiked(Integer postSequence);

    // 도우미 신청, 취소, 신청여부 확인
    ResponseEntity<ResponseDto> postHelperApply(Integer postSequence, String applicantId);
    ResponseEntity<ResponseDto> deleteHelperApply(Integer postSequence, String applicantId);
    ResponseEntity<ResponseDto> getIsApplied(Integer postSequence, String applicantId);


}
