package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;
import com.ongi.ongi_back.common.vo.HelperCommentVO;

import lombok.Getter;

@Getter
public class GetHelperCommentsResponseDto extends ResponseDto {
    private List<HelperCommentVO> comments;
    
    private GetHelperCommentsResponseDto(List<NeedHelperCommentEntity> helperCommentEntities) {
        this.comments = HelperCommentVO.getList(helperCommentEntities);
    }

    public static ResponseEntity<GetHelperCommentsResponseDto> success(List<NeedHelperCommentEntity> helperCommentEntities) {
        GetHelperCommentsResponseDto body = new GetHelperCommentsResponseDto(helperCommentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
