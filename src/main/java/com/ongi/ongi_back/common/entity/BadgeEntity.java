package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.user.PatchBadgeRequestDto;
import com.ongi.ongi_back.common.entity.pk.BadgePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="badge")
@Table(name="badge")
@IdClass(BadgePk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BadgeEntity {
  @Id
  private String userId;
  @Id
  private String badge;
  private Boolean isSelected;

  public void patchBadge(PatchBadgeRequestDto dto, String userId){
    this.userId = userId;
    this.badge = dto.getBadge();
    this.isSelected = true;
  }
}
