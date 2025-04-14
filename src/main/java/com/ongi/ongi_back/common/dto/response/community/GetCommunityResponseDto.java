package com.ongi.ongi_back.common.dto.response.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.vo.CommunityVO;

import lombok.Getter;

@Getter
public class GetCommunityResponseDto extends ResponseDto{
    
    private List<CommunityVO> posts;

    private GetCommunityResponseDto(List<CommunityPostEntity> communityPostEntities) {
        this.posts = CommunityVO.getList(communityPostEntities);
    }

    public static ResponseEntity<GetCommunityResponseDto> success(List<CommunityPostEntity> communityPostEntities) {
        GetCommunityResponseDto body = new GetCommunityResponseDto(communityPostEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    } 
}
