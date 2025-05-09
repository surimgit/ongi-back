package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.admin.PatchNoticeRequestDto;
import com.ongi.ongi_back.common.dto.request.admin.PostNoticeRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PatchQuestionRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="notice")
@Table(name="notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer sequence;

  private String userId;

  private String title;

  private String content;

  private String postDate;
  
  public NoticeEntity(PostNoticeRequestDto dto, String userId){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.userId = userId;
    this.title = dto.getTitle();
    this.content = dto.getTitle();
    this.postDate = now.format(dateTimeFormatter);
  }

  public void patchNotice(PatchNoticeRequestDto dto){
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

}
