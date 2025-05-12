package com.ongi.ongi_back.common.dto.response.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.question.GetQuestionResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;
import com.ongi.ongi_back.common.entity.QuestionEntity;

import lombok.Getter;

@Getter
public class GetChatRoomResponseDto extends ResponseDto{
  private Integer chatSequence;
  private String requesterId;
  private String applicantId;
  private Integer needHelperSequence;
  private Boolean chatAvailable;

  private GetChatRoomResponseDto(ChatEntity chatEntity){
    this.chatSequence = chatEntity.getChatSequence();
    this.requesterId = chatEntity.getRequesterId();
    this.applicantId = chatEntity.getApplicantId();
    this.needHelperSequence = chatEntity.getNeedHelperSequence();
    this.chatAvailable = chatEntity.getChatAvailable();
  }

    public static ResponseEntity<GetChatRoomResponseDto> success(ChatEntity chatEntity){
      GetChatRoomResponseDto body = new GetChatRoomResponseDto(chatEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
