package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.question.PatchQuestionRequestDto;
import com.ongi.ongi_back.common.dto.request.question.PostQuestionRequestDto;
import com.ongi.ongi_back.common.dto.response.Question.GetQuestionListResponseDto;
import com.ongi.ongi_back.common.dto.response.Question.GetQuestionResponseDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.QuestionEntity;
import com.ongi.ongi_back.repository.QuestionRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImplement implements QuestionService {

  private final QuestionRepository questionRepository;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<ResponseDto> postQuestion(PostQuestionRequestDto dto, String userId) {

    try {
      QuestionEntity questionEntity = new QuestionEntity(dto, userId);
      questionRepository.save(questionEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<ResponseDto> patchQuestion(PatchQuestionRequestDto dto, Integer questionSequence, String userId) {
    try {
      QuestionEntity questionEntity = questionRepository.findByQuestionSequence(questionSequence);
      if(questionEntity == null) return ResponseDto.noExistPost();

      String writerId = questionEntity.getUserId();
      if(!writerId.equals(userId)) return ResponseDto.noPermission();

      questionEntity.patchQuestion(dto);
      questionRepository.save(questionEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

      return ResponseDto.success(HttpStatus.OK);
  }

  

  @Override
  public ResponseEntity<? super GetQuestionResponseDto> getQuestion(Integer questionSequence) {
    QuestionEntity questionEntity = null;
    
    try {
      questionEntity = questionRepository.findByQuestionSequence(questionSequence);
      if(questionEntity == null) return ResponseDto.noExistPost();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return GetQuestionResponseDto.success(questionEntity);
  }

  @Override
  public ResponseEntity<? super GetQuestionListResponseDto> getQuestionList(String userId) {
    
    List<QuestionEntity> questionEntities = new ArrayList<>();

    try {
      questionEntities = questionRepository.findAllByOrderByQuestionSequenceDesc();
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetQuestionListResponseDto.success(questionEntities);
  }

  
  
}
