package com.ongi.ongi_back.common.dto.response.badge;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.BadgeEntity;
import com.ongi.ongi_back.common.vo.BadgeVO;

import lombok.Getter;

@Getter
public class GetBadgeListResponseDto extends ResponseDto{
  private List<BadgeVO> badges;

  private GetBadgeListResponseDto(List<BadgeEntity> badgeEntities){
    this.badges = BadgeVO.getBadgeList(badgeEntities);
  }

  public static ResponseEntity<GetBadgeListResponseDto> success(List<BadgeEntity> badgeEntities){
    GetBadgeListResponseDto body = new GetBadgeListResponseDto(badgeEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
