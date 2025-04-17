package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.Notice.GetNoticeListResponseDto;
import com.ongi.ongi_back.common.dto.response.Notice.GetNoticeResponseDto;

public interface NoticeService {
  ResponseEntity<? super GetNoticeResponseDto> getNotice(Integer sequence);
  ResponseEntity<? super GetNoticeListResponseDto> getNoticeList(String userId);

}
