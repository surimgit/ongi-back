package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.List;
import java.util.stream.Collectors;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;

import lombok.Getter;

@Getter
public class GetHelperPostListResponseDto extends ResponseDto {

    private List<GetHelperPostResponseDto> posts;

    public GetHelperPostListResponseDto(List<GetHelperPostResponseDto> posts) {
        this.posts = posts;
    }

    public static GetHelperPostListResponseDto fromEntities(List<NeedHelperEntity> entities) {
        List<GetHelperPostResponseDto> postDtos = entities.stream()
            .map(GetHelperPostResponseDto::new)
            .collect(Collectors.toList());
        return new GetHelperPostListResponseDto(postDtos);
    }
}
