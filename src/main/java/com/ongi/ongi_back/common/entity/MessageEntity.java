package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="message")
@Table(name="message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {

  private Integer chatSequence;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer messageSequence;

  private String content;

  private LocalDateTime chatDate;

  private String fileUrl;

  private Boolean isHelper;

  private String senderId;

  public MessageEntity(SaveMessageRequestDto dto) {
    this.chatSequence = dto.getChatSequence();
    this.content = dto.getContent();
    this.fileUrl = dto.getFileUrl();
    this.chatDate = LocalDateTime.now();
    this.isHelper = true;
    this.senderId = dto.getSenderId(); 
  }
}
