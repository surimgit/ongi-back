package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.admin.PatchAnswerRequestDto;
import com.ongi.ongi_back.common.dto.request.admin.PostNoticeRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.admin.GetIsAdminResponseDto;
import com.ongi.ongi_back.common.entity.NoticeEntity;
import com.ongi.ongi_back.common.entity.QuestionEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.NoticeRepository;
import com.ongi.ongi_back.repository.QuestionRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplement implements AdminService {

  private final QuestionRepository questionRepository;
  private final UserRepository userRepository;
  private final NoticeRepository noticeRepository;

  @Override
  public ResponseEntity<ResponseDto> patchAnswer(PatchAnswerRequestDto dto, Integer questionSequence, String userId) {

    try { 
      QuestionEntity questionEntity = questionRepository.findByQuestionSequence(questionSequence);
      if (questionEntity == null) return ResponseDto.noExistPost();

      UserEntity userEntity = userRepository.findByIsAdminTrue();
      if(!userEntity.getUserId().equals(userId)) return ResponseDto.noPermission();

      questionEntity.patchAnswer(dto);
      questionRepository.save(questionEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<ResponseDto> postNotice(PostNoticeRequestDto dto, String userId) {
    
    try {
      UserEntity userEntity = userRepository.findByIsAdminTrue();
      if(!userEntity.getUserId().equals(userId)) return ResponseDto.noPermission();
      
      NoticeEntity noticeEntity = new NoticeEntity(dto, userId);
      noticeRepository.save(noticeEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.CREATED);

  }
}
