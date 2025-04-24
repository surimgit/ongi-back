package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.entity.pk.LikeKeywordPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="likeKeyword")
@Table(name="like_keyword")
@IdClass(LikeKeywordPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LikeKeywordEntity {
  @Id
  private String userId;
  @Id
  private String keyword; 
  
  public LikeKeywordEntity(AddLikeKeywordRequestDto dto, String userId){
    this.userId = userId;
    this.keyword = dto.getKeyword();
  }

}
