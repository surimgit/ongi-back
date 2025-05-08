package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.List;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetHelperPostListResponseDto extends ResponseDto{

    private List<GetHelperPostResponseDto> posts;

}