package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomListResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;

public interface ChatService {
  void saveMessage(SaveMessageRequestDto sendMessageRequestDto);
  ResponseEntity<ResponseDto> acceptChat(String userId, Integer needHelperSequence, String applicantId);
  ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(String userId);
  ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String userId, Integer chatSequence);
  // ResponseEntity<? super GetChatRoomListResponseDto> leaveChatRoom(String userId, Integer chatSequence);
  // ResponseEntity<? super GetChatRoomListResponseDto> updateChatRoomStatus(String userId, Integer chatSequence, String status);
  
}
