package com.ongi.ongi_back.common.dto.response.question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.QuestionEntity;

import lombok.Getter;

@Getter
public class GetQuestionResponseDto extends ResponseDto{
  private Integer questionSequence;
  private String userId;
  private String postDate;
  private String title;
  private String category;
  private String content;
  private String answer;
  private boolean isAnswered;

  private GetQuestionResponseDto(QuestionEntity questionEntity){
    this.questionSequence = questionEntity.getQuestionSequence();
    this.userId = questionEntity.getUserId();
    this.postDate = questionEntity.getPostDate();
    this.title = questionEntity.getTitle();
    this.category = questionEntity.getCategory();
    this.content = questionEntity.getContent();
    this.answer = questionEntity.getAnswer();
    this.isAnswered = questionEntity.isAnswered();
  }

  public static ResponseEntity<GetQuestionResponseDto> success(QuestionEntity questionEntity){
    GetQuestionResponseDto body = new GetQuestionResponseDto(questionEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
