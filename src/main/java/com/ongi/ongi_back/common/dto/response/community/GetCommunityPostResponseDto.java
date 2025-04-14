package com.ongi.ongi_back.common.dto.response.community;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;

import lombok.Getter;

@Getter
public class GetCommunityPostResponseDto extends ResponseDto {
    private Integer postSequence;
    private String userId;
    private String nickname;
    private String postDate;
    private String category;
    private String title;
    private String content;
    private Integer liked;

    private GetCommunityPostResponseDto(CommunityPostEntity communityPostEntity) {
        this.postSequence = communityPostEntity.getPostSequence();
        this.userId = communityPostEntity.getUserId();
        this.nickname = communityPostEntity.getNickname();
        this.postDate = communityPostEntity.getPostDate();
        this.category = communityPostEntity.getCategory();
        this.title = communityPostEntity.getTitle();
        this.content = communityPostEntity.getContent();
        this.liked = communityPostEntity.getLiked();
    }

    public static ResponseEntity<GetCommunityPostResponseDto> success(CommunityPostEntity communityPostEntity) {
        GetCommunityPostResponseDto body = new GetCommunityPostResponseDto(communityPostEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
