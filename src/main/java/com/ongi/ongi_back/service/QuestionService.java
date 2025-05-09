package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.question.PatchQuestionRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PostQuestionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.question.GetQuestionListResponseDto;
import com.ongi.ongi_back.common.dto.response.question.GetQuestionResponseDto;

public interface QuestionService {
  ResponseEntity<ResponseDto> postQuestion(PostQuestionRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchQuestion(PatchQuestionRequestDto dto, Integer questionSequence, String userId);
  ResponseEntity<ResponseDto> deleteQuestion(Integer questionSequence, String userId);
  
  ResponseEntity<? super GetQuestionResponseDto> getQuestion(Integer questionSequence);
  ResponseEntity<? super GetQuestionListResponseDto> getQuestionList(String userId);
}
