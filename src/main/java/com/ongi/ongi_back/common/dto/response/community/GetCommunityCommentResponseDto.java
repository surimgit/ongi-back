package com.ongi.ongi_back.common.dto.response.community;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;

import lombok.Getter;

@Getter
public class GetCommunityCommentResponseDto extends ResponseDto {
    private String userId;
    private String nickname;
    private Integer postSequence;
    private String content;

    private GetCommunityCommentResponseDto(CommunityCommentEntity communityCommentEntity) {
        this.userId = communityCommentEntity.getUserId();
        this.nickname = communityCommentEntity.getNickname();
        this.postSequence = communityCommentEntity.getPostSequence();
        this.content = communityCommentEntity.getContent();
    }

    public static ResponseEntity<GetCommunityCommentResponseDto> success(CommunityCommentEntity communityCommentEntity) {
       GetCommunityCommentResponseDto body = new GetCommunityCommentResponseDto(communityCommentEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
