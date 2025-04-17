package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.admin.PatchAnswerRequestDto;
import com.ongi.ongi_back.common.dto.request.admin.PostNoticeRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;

  @PatchMapping("/question/{questionSequence}/answer")
  public ResponseEntity<ResponseDto> patchAnswer(
    @RequestBody @Valid PatchAnswerRequestDto requestBody,
    @PathVariable("questionSequence") Integer questionSequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = adminService.patchAnswer(requestBody, questionSequence, userId);
    return response;
  }

  @PostMapping("/notice")
  public ResponseEntity<ResponseDto> postNotice(
    @RequestBody @Valid PostNoticeRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) {
      ResponseEntity<ResponseDto> response = adminService.postNotice(requestBody, userId);
      return response;
  }
  
}
