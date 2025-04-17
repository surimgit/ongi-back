package com.ongi.ongi_back.common.dto.response.Notice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NoticeEntity;
import com.ongi.ongi_back.common.vo.NoticeVO;

import lombok.Getter;

@Getter
public class GetNoticeListResponseDto extends ResponseDto{
  private List<NoticeVO> notices;

  private GetNoticeListResponseDto(List<NoticeEntity> noticeEntities){
    this.notices = NoticeVO.getNoticeList(noticeEntities);
  }

  public static ResponseEntity<GetNoticeListResponseDto> success(List<NoticeEntity> noticeEntities){
    GetNoticeListResponseDto body = new GetNoticeListResponseDto(noticeEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
