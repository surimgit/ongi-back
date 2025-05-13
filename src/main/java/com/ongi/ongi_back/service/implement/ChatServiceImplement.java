package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;
import com.ongi.ongi_back.common.entity.HelperApplyEntity;
import com.ongi.ongi_back.common.entity.MessageEntity;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;
import com.ongi.ongi_back.repository.ChatRepository;
import com.ongi.ongi_back.repository.HelperApplyRepository;
import com.ongi.ongi_back.repository.HelperPostRepository;
import com.ongi.ongi_back.repository.MessageRepository;
import com.ongi.ongi_back.service.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService{
  
  private final MessageRepository messageRepository;
  private final ChatRepository chatRepository;
  private final HelperApplyRepository helperApplyRepository;
  @Override
  public void saveMessage(SaveMessageRequestDto saveMessageRequestDto) {
    MessageEntity messageEntity = new MessageEntity(saveMessageRequestDto);
    messageRepository.save(messageEntity);
  }

  @Override
  public ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String requesterId, Integer chatSequence) {
    ChatEntity chatEntity = null;
    
    try {
      chatEntity = chatRepository.findByChatSequenceAndRequesterId(chatSequence, requesterId);
      if(chatEntity == null) return ResponseDto.noExistChatRoom();
      if(chatEntity.getChatAvailable() == false) return ResponseDto.noPermission(); 
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }  
  
    return GetChatRoomResponseDto.success(chatEntity);
  
  }

  @Override
  public ResponseEntity<ResponseDto> acceptChat(String requesterId, Integer needHelperSequence, String applicantId) {
    
    ChatEntity chatEntity = null;
    try {
      chatEntity = chatRepository.findByRequesterIdAndApplicantId(requesterId, applicantId);
      
      if(chatEntity == null) return ResponseDto.noExistChatRoom();

      chatEntity.setChatAvailable(true);
      chatRepository.save(chatEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return ResponseDto.success(HttpStatus.OK);
  }
  

}
