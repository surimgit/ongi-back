package com.ongi.ongi_back.common.dto.response.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.vo.CommunityCommentVO;

import lombok.Getter;

@Getter
public class GetCommunityCommentsResponseDto extends ResponseDto {
    private List<CommunityCommentVO> comments;
    
    private GetCommunityCommentsResponseDto(List<CommunityCommentEntity> communityCommentEntities) {
        this.comments = CommunityCommentVO.getList(communityCommentEntities);
    }

    public static ResponseEntity<GetCommunityCommentsResponseDto> success(List<CommunityCommentEntity> communityCommentEntities) {
        GetCommunityCommentsResponseDto body = new GetCommunityCommentsResponseDto(communityCommentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
