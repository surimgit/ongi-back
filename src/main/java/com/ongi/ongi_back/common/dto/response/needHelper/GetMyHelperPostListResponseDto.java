package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;
import com.ongi.ongi_back.common.vo.NeedHelperPostVO;

import lombok.Getter;

@Getter
public class GetMyHelperPostListResponseDto extends ResponseDto{
  private List<NeedHelperPostVO> posts;

  private GetMyHelperPostListResponseDto(List<NeedHelperEntity> needHelperEntities){
    this.posts = NeedHelperPostVO.getList(needHelperEntities);
  }
  
  public static ResponseEntity<GetMyHelperPostListResponseDto> success(List<NeedHelperEntity> needHelperEntities){
    GetMyHelperPostListResponseDto body = new GetMyHelperPostListResponseDto(needHelperEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
