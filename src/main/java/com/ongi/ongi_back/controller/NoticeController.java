package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.Notice.GetNoticeListResponseDto;
import com.ongi.ongi_back.common.dto.response.Notice.GetNoticeResponseDto;
import com.ongi.ongi_back.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {
  
  private final NoticeService noticeService;

  @GetMapping({"/{sequence}"})
  public ResponseEntity<? super GetNoticeResponseDto> getNotice(
    @PathVariable("sequence") Integer sequence
  )
  {
    ResponseEntity<? super GetNoticeResponseDto> response = noticeService.getNotice(sequence);
    return response;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList(
    @AuthenticationPrincipal String userId
  )
  {
    ResponseEntity<? super GetNoticeListResponseDto> response = noticeService.getNoticeList(userId);
    return response;
  }
}
