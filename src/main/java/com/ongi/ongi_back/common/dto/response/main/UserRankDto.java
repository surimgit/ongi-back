package com.ongi.ongi_back.common.dto.response.main;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UserRankDto extends ResponseDto{

    private String userId;
    private String nickname;
    private Integer totalActivity;

    public UserRankDto(String userId, String nickname, Integer totalActivity) {
        this.userId = userId;
        this.nickname = nickname;
        this.totalActivity = totalActivity;
    }
}