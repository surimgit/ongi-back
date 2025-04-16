package com.ongi.ongi_back.common.dto.response.Question;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.QuestionEntity;
import com.ongi.ongi_back.common.vo.QuestionVO;

import lombok.Getter;

@Getter
public class GetQuestionListResponseDto extends ResponseDto{
  private List<QuestionVO> questions;

  private GetQuestionListResponseDto(List<QuestionEntity> questionEntities){
    this.questions = QuestionVO.getList(questionEntities);
  }

  public static ResponseEntity<GetQuestionListResponseDto> success(List<QuestionEntity> questionEntities){
    GetQuestionListResponseDto body = new GetQuestionListResponseDto(questionEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }


}
