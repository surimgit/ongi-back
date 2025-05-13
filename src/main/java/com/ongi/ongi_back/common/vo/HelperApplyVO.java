package com.ongi.ongi_back.common.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.HelperApplyEntity;

import lombok.Getter;

@Getter
public class HelperApplyVO {
  private String applicantId;
  private LocalDateTime requestedAt;
  private Boolean isApplied;
  private Integer postSequence;
  private Integer chatSequence;

  private HelperApplyVO(HelperApplyEntity helperApplyEntity){
    this.applicantId = helperApplyEntity.getApplicantId();
    this.requestedAt = helperApplyEntity.getRequestedAt();
    this.isApplied = helperApplyEntity.getIsApplied();
    this.postSequence = helperApplyEntity.getPostSequence();
    this.chatSequence = helperApplyEntity.getChatSequence();
  }
  
  public static List<HelperApplyVO> getList(List<HelperApplyEntity> helperApplyEntities){
    List<HelperApplyVO> list = new ArrayList<>();
    for(HelperApplyEntity helperApplyEntity : helperApplyEntities){
      HelperApplyVO vo = new HelperApplyVO(helperApplyEntity);
      list.add(vo);
    }

    return list;
  }
}
