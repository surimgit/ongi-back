package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.BadgeEntity;

import lombok.Getter;

@Getter
public class BadgeVO {
  private String userId;
  private String badge;

  private BadgeVO(BadgeEntity badgeEntity){
    this.userId = badgeEntity.getUserId();
    this.badge = badgeEntity.getBadge();
  }

  public static List<BadgeVO> getBadgeList(List<BadgeEntity> badgeEntities){
    List<BadgeVO> list = new ArrayList<>();
    for(BadgeEntity badgeEntity : badgeEntities){
      BadgeVO vo = new BadgeVO(badgeEntity);
      list.add(vo);
    }
    return list;
  }
}
