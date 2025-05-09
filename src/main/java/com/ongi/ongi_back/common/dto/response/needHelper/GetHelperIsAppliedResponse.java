package com.ongi.ongi_back.common.dto.response.needHelper;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.HelperApplyEntity;

import lombok.Getter;

@Getter
public class GetHelperIsAppliedResponse extends ResponseDto {
    private Boolean isApplied;

    public GetHelperIsAppliedResponse (HelperApplyEntity helperApplyEntity){
        this.isApplied = helperApplyEntity.getIsApplied();
    }
}
