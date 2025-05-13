package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;

public interface ChatService {
  void saveMessage(SaveMessageRequestDto sendMessageRequestDto);
  ResponseEntity<ResponseDto> acceptChat(String userId, Integer needHelperSequence, String applicantId);
  ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String requesterId, Integer chatSequence);
}
