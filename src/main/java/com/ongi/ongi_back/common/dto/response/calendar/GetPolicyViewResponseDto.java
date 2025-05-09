package com.ongi.ongi_back.common.dto.response.calendar;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.YouthCenterEntity;
import com.ongi.ongi_back.common.vo.YouthCenterViewVO;

import lombok.Getter;

@Getter
public class GetPolicyViewResponseDto extends ResponseDto{
    private List<YouthCenterViewVO> policies;

    private GetPolicyViewResponseDto(List<YouthCenterEntity> policies){
        this.policies = YouthCenterViewVO.getList(policies);
    }

    public static ResponseEntity<GetPolicyViewResponseDto> success(List<YouthCenterEntity> policies){
        GetPolicyViewResponseDto body = new GetPolicyViewResponseDto(policies);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
