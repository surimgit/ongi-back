package com.ongi.ongi_back.common.dto.response.needHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;

import lombok.Getter;

@Getter
public class GetChatReseopnseDto extends ResponseDto{
  private Integer chatSequence;
  private String helperId;
  private Integer needHelperSequence;
  private Boolean chatAvaiable;

  private GetChatReseopnseDto(ChatEntity chatEntity) {
    this.chatSequence = chatEntity.getChatSequnece();
    this.helperId = chatEntity.getHelperId();
    this.needHelperSequence = chatEntity.getNeedHelperSequence();
    this.chatAvaiable = chatEntity.getChatAvaiable();
  }

  public static ResponseEntity<GetChatReseopnseDto> success(ChatEntity chatEntity){
    GetChatReseopnseDto body = new GetChatReseopnseDto(chatEntity);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
