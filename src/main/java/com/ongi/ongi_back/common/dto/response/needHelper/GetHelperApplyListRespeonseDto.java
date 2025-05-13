package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.HelperApplyEntity;
import com.ongi.ongi_back.common.vo.HelperApplyVO;

import lombok.Getter;

@Getter
public class GetHelperApplyListRespeonseDto extends ResponseDto{
  private List<HelperApplyVO> applies;

  private GetHelperApplyListRespeonseDto(List<HelperApplyEntity> helperApplyEntities){
    this.applies = HelperApplyVO.getList(helperApplyEntities);
  }

  public static ResponseEntity<GetHelperApplyListRespeonseDto> success(List<HelperApplyEntity> helperApplyEntities){
    GetHelperApplyListRespeonseDto body = new GetHelperApplyListRespeonseDto(helperApplyEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
