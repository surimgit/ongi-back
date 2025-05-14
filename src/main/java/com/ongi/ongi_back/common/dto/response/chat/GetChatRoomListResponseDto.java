package com.ongi.ongi_back.common.dto.response.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;

import lombok.Getter;

@Getter
public class GetChatRoomListResponseDto extends ResponseDto{
    private List<GetChatRoomResponseDto> chatList;

  private GetChatRoomListResponseDto(List<ChatEntity> entities) {
    this.chatList = entities.stream()
      .map(GetChatRoomResponseDto::new)
      .toList();
  }

  public static ResponseEntity<GetChatRoomListResponseDto> success(List<ChatEntity> entities) {
    return ResponseEntity.ok(new GetChatRoomListResponseDto(entities));
  }
}
