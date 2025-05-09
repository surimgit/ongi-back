package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.question.PatchQuestionRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PostQuestionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.question.GetQuestionListResponseDto;
import com.ongi.ongi_back.common.dto.response.question.GetQuestionResponseDto;
import com.ongi.ongi_back.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/mypage/question")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping({"", "/"})
  public ResponseEntity<ResponseDto> postQuestion(
    @RequestBody @Valid PostQuestionRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = questionService.postQuestion(requestBody, userId);
    return response;
  }

  @PatchMapping("/{questionSequence}")
  public ResponseEntity<ResponseDto> patchQuestion(
    @RequestBody @Valid PatchQuestionRequestDto requestBody,
    @PathVariable("questionSequence") Integer questionSequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = questionService.patchQuestion(requestBody, questionSequence, userId);
    return response;
  }

  @GetMapping("/{questionSequence}")
  public ResponseEntity<? super GetQuestionResponseDto> getQuestion(
    @PathVariable("questionSequence") Integer questionSequence
  ){
    ResponseEntity<? super GetQuestionResponseDto> response = questionService.getQuestion(questionSequence);
    return response;
  }
  
  @DeleteMapping("/{questionSequence}")
  public ResponseEntity<ResponseDto> deleteQuestion(
    @PathVariable("questionSequence") Integer questionSequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = questionService.deleteQuestion(questionSequence, userId);
    return response;
  }
  
  @GetMapping({"", "/"})
  public ResponseEntity<? super GetQuestionListResponseDto> getQuestionList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetQuestionListResponseDto> response = questionService.getQuestionList(userId);
    return response;
  }
  
}
