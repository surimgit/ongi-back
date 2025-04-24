package com.ongi.ongi_back.common.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.vo.LikeKeywordVO;

import lombok.Getter;

@Getter
public class GetLikeKeywordListResponseDto extends ResponseDto{
  private List<LikeKeywordVO> likeKeywords;

  private GetLikeKeywordListResponseDto(List<LikeKeywordEntity> likeKeywordEntities){
    this.likeKeywords = LikeKeywordVO.getLikeKeywordList(likeKeywordEntities);
  }

  public static ResponseEntity<GetLikeKeywordListResponseDto> success(List<LikeKeywordEntity> likeKeywordEntities){
    GetLikeKeywordListResponseDto body = new GetLikeKeywordListResponseDto(likeKeywordEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
