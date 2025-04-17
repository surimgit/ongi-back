package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.admin.PatchAnswerRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PatchQuestionRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PostQuestionRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="question")
@Table(name="question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class QuestionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer questionSequence;
  
  private String userId;
  private String postDate;
  private String title;
  private String category;
  private String content;
  private String answer;  
  private boolean isAnswered;

  public QuestionEntity(PostQuestionRequestDto dto, String userId){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.userId = userId;
    this.postDate =  now.format(dateTimeFormatter);
    this.title = dto.getTitle();
    this.category = dto.getCategory();
    this.content = dto.getContent();
    this.isAnswered = false;
  }

  public void patchAnswer(PatchAnswerRequestDto dto){
    this.answer = dto.getAnswer();
    this.isAnswered = true;
  }

  public void patchQuestion(PatchQuestionRequestDto dto){
    this.title = dto.getTitle();
    this.category = dto.getCategory();
    this.content = dto.getContent();
  }
}
