package com.ongi.ongi_back.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.chat.SaveMessageRequestDto;
import com.ongi.ongi_back.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
  private final ChatService chatService;
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/chat.sendMessage")
  public void sendMessage(SaveMessageRequestDto saveMessageRequestDto) {
    log.info("[ChatController] Received Message: {}", saveMessageRequestDto);
    chatService.saveMessage(saveMessageRequestDto);
    messagingTemplate.convertAndSend(
        "/topic/chat/" + saveMessageRequestDto.getChatSequence(),
        saveMessageRequestDto
    );
    
  }
}
