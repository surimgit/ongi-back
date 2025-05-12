package com.ongi.ongi_back.common.dto.response.community;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.PostImageEntity;

import lombok.Getter;

@Getter
public class GetCommunityPostResponseDto extends ResponseDto {
    private Integer postSequence;
    private String userId;
    private String nickname;
    private String postDate;
    private String board;
    private String category;
    private String title;
    private String content;
    private Integer liked;
    private Integer viewCount;

    private GetCommunityPostResponseDto(CommunityPostEntity communityPostEntity) {
        this.postSequence = communityPostEntity.getPostSequence();
        this.userId = communityPostEntity.getUserId();
        this.nickname = communityPostEntity.getNickname();
        this.postDate = communityPostEntity.getPostDate();
        this.board = communityPostEntity.getBoard();
        this.category = communityPostEntity.getCategory();
        this.title = communityPostEntity.getTitle();
        this.content = communityPostEntity.getContent();
        this.liked = communityPostEntity.getLiked();
        this.viewCount = communityPostEntity.getViewCount();
    }

    public static ResponseEntity<GetCommunityPostResponseDto> success(CommunityPostEntity communityPostEntity) {
        GetCommunityPostResponseDto body = new GetCommunityPostResponseDto(communityPostEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
