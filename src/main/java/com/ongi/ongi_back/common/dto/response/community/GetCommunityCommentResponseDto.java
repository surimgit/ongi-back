package com.ongi.ongi_back.common.dto.response.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.vo.CommunityCommentVO;

import lombok.Getter;

@Getter
public class GetCommunityCommentResponseDto extends ResponseDto {
    private List<CommunityCommentVO> comments;
    
    private GetCommunityCommentResponseDto(List<CommunityCommentEntity> communityCommentEntities) {
        this.comments = CommunityCommentVO.getList(communityCommentEntities);
    }

    public static ResponseEntity<GetCommunityCommentResponseDto> success(List<CommunityCommentEntity> communityCommentEntities) {
        GetCommunityCommentResponseDto body = new GetCommunityCommentResponseDto(communityCommentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
