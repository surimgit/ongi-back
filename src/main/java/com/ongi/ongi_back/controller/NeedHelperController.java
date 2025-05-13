package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.needHelper.PatchHelperPostRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperLikedResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto;
import com.ongi.ongi_back.service.NeedHelperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/needHelper")
@RequiredArgsConstructor
public class NeedHelperController {

    private final NeedHelperService needHelperService;

    @PostMapping({"/write"})
    public ResponseEntity<ResponseDto> postHelper(
        @RequestBody @Valid PostHelperRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = needHelperService.postHelper(requestBody, userId);
        return response;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<? super GetHelperPostListResponseDto> getHelperPostList() {
        ResponseEntity<? super GetHelperPostListResponseDto> response = needHelperService.getHelperPostList();
        return response;
    }

    @GetMapping({"/{sequence}"})
    public ResponseEntity<? super GetHelperPostResponseDto> getHelperPost(
        @PathVariable("sequence") Integer sequence
    )   {
        ResponseEntity<? super GetHelperPostResponseDto> response = needHelperService.getHelperPost(sequence);
        return response;
    }

    @PatchMapping({"/{sequence}"})
    public ResponseEntity<ResponseDto> patchCommunityPost(
        @RequestBody @Valid PatchHelperPostRequestDto requestBody,
        @PathVariable("sequence") Integer sequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = needHelperService.patchHelperPost(requestBody, sequence, userId);
        return response;
    }

    @DeleteMapping({"/{postSequence}"})
    public ResponseEntity<ResponseDto> deleteHelperPost(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = needHelperService.deleteHelperPost(postSequence, userId);
        return response;
    }

    @PostMapping("/{postSequence}/comment")
    public ResponseEntity<ResponseDto> postHelperComment(
        @RequestBody @Valid PostHelperCommentRequestDto requestBody,
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = needHelperService.postComment(requestBody, postSequence, userId);
        return response;
    }

    @DeleteMapping("/{postSequence}/comment/{commentSequence}")
    public ResponseEntity<ResponseDto> deleteHelperComment(
        @PathVariable("postSequence") Integer postSequence,
        @PathVariable("commentSequence") Integer commentSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = needHelperService.deleteHelperComment(postSequence, commentSequence, userId);
        return response;
    }

    @GetMapping("/{postSequence}/comment")
    public ResponseEntity<? super GetHelperCommentsResponseDto> getHelperComments(
        @PathVariable("postSequence") Integer postSequence
    )   {
        ResponseEntity<? super GetHelperCommentsResponseDto> response = needHelperService.getHelperComments(postSequence);
        return response;
    }

    @GetMapping("/{postSequence}/comment/{commentSequence}")
    public ResponseEntity<? super GetHelperCommentResponseDto> getHelperComment(
        @PathVariable("postSequence") Integer postSequence,
        @PathVariable("commentSequence") Integer commentSequence
    )   {
        ResponseEntity<? super GetHelperCommentResponseDto> response = needHelperService.getHelperComment(postSequence, commentSequence);
        return response;
    }

    @PutMapping("/{postSequence}/liked")
    public ResponseEntity<ResponseDto> putLiked(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId
    )   {
        ResponseEntity<ResponseDto> response = needHelperService.putHelperLiked(postSequence, userId);
        return response;
    }

    @GetMapping("/{postSequence}/liked")
    public ResponseEntity<? super GetHelperLikedResponseDto> getHelperliked(
        @PathVariable("postSequence") Integer postSequence
    )   {
        ResponseEntity<? super GetHelperLikedResponseDto> response = needHelperService.getHelperLiked(postSequence);
        return response;
    }
    
    @PostMapping("/{postSequence}/apply")
    public ResponseEntity<ResponseDto> postHelperApply(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String applicantId
    ) {
        ResponseEntity<ResponseDto> response = needHelperService.postHelperApply(postSequence, applicantId);
        return response;
    }

    @DeleteMapping("/{postSequence}/apply")
    public ResponseEntity<ResponseDto> deleteHelperApply(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String applicantId
    ) {
        return needHelperService.deleteHelperApply(postSequence, applicantId);
    }

    @GetMapping("/{postSequence}/apply")
    public ResponseEntity<ResponseDto> getIsApplied(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String applicantId
    ) {
        return needHelperService.getIsApplied(postSequence, applicantId);
    }

    
    @PatchMapping("/{postSequence}/apply")
    public void accpetApply(
        @PathVariable("postSequence") Integer postSequence,
        @AuthenticationPrincipal String userId,
        @RequestParam("applicantId") String applicantId
    ) {
        needHelperService.accpetApply(postSequence, applicantId, userId);
    }
    
}
