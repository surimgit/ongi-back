package com.ongi.ongi_back.common.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.NeedHelperEntity;

import lombok.Getter;

@Getter
public class NeedHelperPostVO {
  private Integer sequence;
  private LocalDateTime date;
  private String title;
  private String schedule;
  private Boolean isRequestSolved;

  private NeedHelperPostVO(NeedHelperEntity needHelperEntity){
    this.sequence = needHelperEntity.getSequence();
    this.date = needHelperEntity.getDate();
    this.title = needHelperEntity.getTitle();
    this.schedule = needHelperEntity.getSchedule();
    this.isRequestSolved = needHelperEntity.getIsRequestSolved();
  }
  
  public static List<NeedHelperPostVO> getList(List<NeedHelperEntity> needHelperEntities){
    List<NeedHelperPostVO> list = new ArrayList<>();
    for(NeedHelperEntity needHelperEntity : needHelperEntities){
      NeedHelperPostVO vo = new NeedHelperPostVO(needHelperEntity);
      list.add(vo);
    }
    return list;
  }

}
