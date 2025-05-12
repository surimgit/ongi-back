package com.ongi.ongi_back.common.dto.request.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostChatRoomRequestDto {
    @NotBlank
    private String requesterId;
    @NotBlank
    private String applicnatId;
    @NotNull
    private Integer needHelperSequence; //post_sequence
}
