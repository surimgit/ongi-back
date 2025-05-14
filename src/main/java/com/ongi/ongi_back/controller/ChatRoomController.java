package com.ongi.ongi_back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomListResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;
import com.ongi.ongi_back.common.entity.MessageEntity;
import com.ongi.ongi_back.repository.MessageRepository;
import com.ongi.ongi_back.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatRoomController {
  private final ChatService chatService;
  private final MessageRepository messageRepository;

  @GetMapping({ "" })
  public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(
      @AuthenticationPrincipal String userId) {
    ResponseEntity<? super GetChatRoomListResponseDto> response = chatService.getChatRoomList(userId);
    return response;
  }

  @GetMapping("/{chatSequence}/message")
  public List<MessageEntity> getChatMessages(
      @PathVariable Integer chatSequence,
      @AuthenticationPrincipal String userId) {
    return messageRepository.findByChatSequenceOrderByChatDateAsc(chatSequence);
  }

  @GetMapping("/{chatSequence}/latest")
  public MessageEntity getLatestMessage(
      @PathVariable Integer chatSequence,
      @AuthenticationPrincipal String userId) {
    return messageRepository.findFirstByChatSequenceOrderByChatDateDesc(chatSequence);
  }

  @PatchMapping("/{chatSequence}")
  public ResponseEntity<ResponseDto> acceptChat(
      @PathVariable("chatSequence") Integer chatSequence,
      @AuthenticationPrincipal String userId,
      @RequestParam("applicantId") String applicantId) {
    ResponseEntity<ResponseDto> response = chatService.acceptChat(userId, chatSequence, applicantId);
    return response;
  }

}
