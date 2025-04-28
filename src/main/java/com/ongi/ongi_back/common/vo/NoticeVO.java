package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class NoticeVO {
  private Integer sequence;
  private String postDate;
  private String title;

  private NoticeVO(NoticeEntity noticeEntity){
    this.sequence = noticeEntity.getSequence();
    this.postDate = noticeEntity.getPostDate();
    this.title = noticeEntity.getTitle();
  }

  public static List<NoticeVO> getNoticeList(List<NoticeEntity> noticeEntities){
    List<NoticeVO> list = new ArrayList<>();
    for(NoticeEntity noticeEntity: noticeEntities){
      NoticeVO vo = new NoticeVO(noticeEntity);
      list.add(vo);
    }
    return list;
  }
}
