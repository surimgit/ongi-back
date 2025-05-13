package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.chat.GetChatRoomResponseDto;
import com.ongi.ongi_back.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatRoomController {
  private final ChatService chatService;

  @GetMapping({"/{chatSequence}"})
  public ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(
    @PathVariable("chatSequence") Integer chatSequence,
    @AuthenticationPrincipal String requesterId
  )
  {
    ResponseEntity<? super GetChatRoomResponseDto> response = chatService.getChatRoom(requesterId, chatSequence);
    return response;
  }

  @PatchMapping("/{chatSequence}")
  public ResponseEntity<ResponseDto> acceptChat(
    @PathVariable("chatSequence") Integer chatSequence,
    @AuthenticationPrincipal String userId,
    @RequestParam("applicantId") String applicantId
  ){
    ResponseEntity<ResponseDto> response = chatService.acceptChat(chatSequence, userId, applicantId);
    return response;
  }

}
