package com.ongi.ongi_back.common.dto.response.badge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.BadgeEntity;

import lombok.Getter;

@Getter
public class GetBadgeResponseDto extends ResponseDto{
  private String userId;
  private String badge;
  private Boolean isSelected;

  private GetBadgeResponseDto(BadgeEntity badgeEntity){
  this.userId = badgeEntity.getUserId();
  this.badge = badgeEntity.getBadge();
  this.isSelected = badgeEntity.getIsSelected();
  }

  public static ResponseEntity<GetBadgeResponseDto> success(BadgeEntity badgeEntity){
    GetBadgeResponseDto body = new GetBadgeResponseDto(badgeEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
