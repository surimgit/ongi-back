package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.LikeKeywordEntity;

import lombok.Getter;

@Getter
public class LikeKeywordVO {
  private String userId;
  private String keyword;

  private LikeKeywordVO(LikeKeywordEntity likeKeywordEntity){
    this.userId = likeKeywordEntity.getUserId();
    this.keyword = likeKeywordEntity.getKeyword();
  }

  public static List<LikeKeywordVO> getLikeKeywordList(List<LikeKeywordEntity> likeKeywordEntities){
    List<LikeKeywordVO> list = new ArrayList<>();
    for(LikeKeywordEntity likeKeywordEntity : likeKeywordEntities){
      LikeKeywordVO vo = new LikeKeywordVO(likeKeywordEntity);
      list.add(vo);
    }
    return list;
  }
}
