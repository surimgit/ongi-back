package com.ongi.ongi_back.common.dto.response.calendar;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.YouthCenterEntity;
import com.ongi.ongi_back.common.vo.YouthCenterListVO;

import lombok.Getter;

@Getter
public class GetPolicyListResponseDto extends ResponseDto {
    
    private List<YouthCenterListVO> policies;

    private GetPolicyListResponseDto(List<YouthCenterEntity> policies){
        this.policies = YouthCenterListVO.getList(policies);
    }

    public static ResponseEntity<GetPolicyListResponseDto> success(List<YouthCenterEntity> policies){
        GetPolicyListResponseDto body = new GetPolicyListResponseDto(policies);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
