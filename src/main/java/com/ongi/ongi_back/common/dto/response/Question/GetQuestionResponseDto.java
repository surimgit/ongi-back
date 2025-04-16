package com.ongi.ongi_back.common.dto.response.Question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.QuestionEntity;

import lombok.Getter;

@Getter
public class GetQuestionResponseDto extends ResponseDto{
  private String postDate;
  private String category;
  private String title;
  private String content;
  private String answer;

  private GetQuestionResponseDto(QuestionEntity questionEntity){
    this.postDate = questionEntity.getPostDate();
    this.category = questionEntity.getCategory();
    this.title = questionEntity.getTitle();
    this.content = questionEntity.getContent();
    this.answer = questionEntity.getAnswer();
  }

  public static ResponseEntity<GetQuestionResponseDto> success(QuestionEntity questionEntity){
    GetQuestionResponseDto body = new GetQuestionResponseDto(questionEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
