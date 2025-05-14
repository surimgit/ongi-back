package com.ongi.ongi_back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomListResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;
import com.ongi.ongi_back.common.entity.MessageEntity;
import com.ongi.ongi_back.repository.ChatRepository;
import com.ongi.ongi_back.repository.HelperApplyRepository;
import com.ongi.ongi_back.repository.MessageRepository;
import com.ongi.ongi_back.service.ChatService;

import lombok.RequiredArgsConstructor;

// ... 생략
@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService {

  private final MessageRepository messageRepository;
  private final ChatRepository chatRepository;

  @Override
  public void saveMessage(SaveMessageRequestDto saveMessageRequestDto) {
    MessageEntity messageEntity = new MessageEntity(saveMessageRequestDto);
    messageRepository.save(messageEntity);
  }

  @Override
  public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(String userId) {
    List<ChatEntity> chatEntities;
    try {
      chatEntities = chatRepository.findByRequesterIdOrApplicantId(userId, userId);
      if (chatEntities == null)
        return ResponseDto.noExistChatRoom();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetChatRoomListResponseDto.success(chatEntities);
  }

  @Override
  public ResponseEntity<ResponseDto> acceptChat(String requesterId, Integer chatSequence, String applicantId) {
    try {
      ChatEntity chatEntity = chatRepository.findByRequesterIdAndChatSequence(requesterId, chatSequence);
      if (chatEntity == null)
        return ResponseDto.noExistChatRoom();
      chatEntity.setChatAvailable(true);
      chatRepository.save(chatEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String userId, Integer chatSequence) {
    ChatEntity chatEntity = null;
    try {
      chatEntity = chatRepository.findByRequesterIdAndChatSequence(userId, chatSequence);
      if (chatEntity == null)
        return ResponseDto.noExistChatRoom();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetChatRoomResponseDto.success(chatEntity);
  }

}
