package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.community.FindCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostInfoPostRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.service.CommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {
    
    private final CommunityService communityService;

    @PostMapping({"", "/"})
    public ResponseEntity<ResponseDto> postInformationPost(
        @RequestBody @Valid PostInfoPostRequestDto requestBody,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = communityService.postInfo(requestBody, userId);
        return response;
    }

    @DeleteMapping({"/{postSequence}"})
    public ResponseEntity<ResponseDto> deleteCommunityPost(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = communityService.deleteInfo(postSequence, userId);
        return response;
    }

    @GetMapping({"/{postSequence}"})
    public ResponseEntity<? super GetCommunityPostResponseDto> getCommunityPost(
        @PathVariable("postSequence") Integer postSequence
    )   {
        ResponseEntity<? super GetCommunityPostResponseDto> response = communityService.getCommunityPost(postSequence);
        return response;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<? super GetCommunityResponseDto> getCommunity() {
        ResponseEntity<? super GetCommunityResponseDto> response = communityService.getCommunity();
        return response;
    }

    @GetMapping({"/writer"})
    public ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByWriter(
        @Valid FindCommunityPostRequestDto dto
    )   {
        String nickname = dto.getKeyword();
        ResponseEntity<? super GetCommunityResponseDto> response = communityService.getCommunityByWriter(nickname);
        return response;
    }

    @PostMapping("/{postSequence}/comment")
    public ResponseEntity<ResponseDto> postCommunityComment(
        @RequestBody @Valid PostCommentRequestDto requestBody,
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = communityService.postComment(requestBody, postSequence, userId);
        return response;
    }
}
