package com.ongi.ongi_back.common.dto.response.notice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class GetNoticeResponseDto extends ResponseDto{
  private String postDate;
  private String title;
  private String content;
  private String userId;

  private GetNoticeResponseDto(NoticeEntity noticeEntity){
    this.postDate = noticeEntity.getPostDate();
    this.title = noticeEntity.getTitle();
    this.content = noticeEntity.getContent();
    this.userId = noticeEntity.getUserId();
  }

  public static ResponseEntity<GetNoticeResponseDto> success(NoticeEntity noticeEntity){
    GetNoticeResponseDto body = new GetNoticeResponseDto(noticeEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
    
  }
}
