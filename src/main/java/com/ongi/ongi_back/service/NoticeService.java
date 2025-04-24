package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.response.notice.GetNoticeListResponseDto;
import com.ongi.ongi_back.common.dto.response.notice.GetNoticeResponseDto;

public interface NoticeService {
  ResponseEntity<? super GetNoticeResponseDto> getNotice(Integer sequence);
  ResponseEntity<? super GetNoticeListResponseDto> getNoticeList(String userId);

}
