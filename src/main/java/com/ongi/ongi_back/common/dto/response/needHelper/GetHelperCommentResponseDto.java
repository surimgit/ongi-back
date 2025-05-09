package com.ongi.ongi_back.common.dto.response.needHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;

import lombok.Getter;

@Getter
public class GetHelperCommentResponseDto extends ResponseDto {
    private String userId;
    private String nickname;
    private Integer postSequence;
    private String content;

    private GetHelperCommentResponseDto(NeedHelperCommentEntity helperCommentEntity) {
        this.userId = helperCommentEntity.getUserId();
        this.nickname = helperCommentEntity.getNickname();
        this.postSequence = helperCommentEntity.getPostSequence();
        this.content = helperCommentEntity.getContent();
    }

    public static ResponseEntity<GetHelperCommentResponseDto> success(NeedHelperCommentEntity helperCommentEntity) {
        GetHelperCommentResponseDto body = new GetHelperCommentResponseDto(helperCommentEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
