package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.response.notice.GetNoticeListResponseDto;
import com.ongi.ongi_back.common.dto.response.notice.GetNoticeResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NoticeEntity;
import com.ongi.ongi_back.repository.NoticeRepository;
import com.ongi.ongi_back.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService{
  
  private final NoticeRepository noticeRepository;

  @Override
  public ResponseEntity<? super GetNoticeResponseDto> getNotice(Integer sequence) {
    NoticeEntity noticeEntity = null;

    try {
      noticeEntity = noticeRepository.findBySequence(sequence);
      if(noticeEntity == null) return ResponseDto.noExistPost();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return GetNoticeResponseDto.success(noticeEntity);
  }

  @Override
  public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList(String userId) {
    List<NoticeEntity> noticeEntities = new ArrayList<>();

    try {
      noticeEntities = noticeRepository.findAllByOrderBySequenceDesc();
      

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetNoticeListResponseDto.success(noticeEntities);
  }
  

  
}
