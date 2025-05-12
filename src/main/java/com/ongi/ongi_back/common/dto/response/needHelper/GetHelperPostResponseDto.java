package com.ongi.ongi_back.common.dto.response.needHelper;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetHelperPostResponseDto extends ResponseDto {

    private Integer sequence;
    private String userId;
    private String nickname;
    private String title;
    private String content;
    private String schedule;
    private Boolean isRequestSolved;
    private String meetingType;
    private String city;
    private String district;
    private String date;
    private String reward;
    private String keyword;

    public GetHelperPostResponseDto(NeedHelperEntity entity, String nickname) {
        this.sequence = entity.getSequence();
        this.userId = entity.getUserId();
        this.nickname = nickname;
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.schedule = entity.getSchedule();
        this.isRequestSolved = entity.getIsRequestSolved();
        this.meetingType = entity.getMeetingType();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.date = entity.getDate().toString();
        this.reward = entity.getReward();
        this.keyword = entity.getKeyword();
    }

    public GetHelperPostResponseDto(NeedHelperEntity entity) {
        this.sequence = entity.getSequence();
        this.userId = entity.getUserId();
        this.nickname = nickname;
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.schedule = entity.getSchedule();
        this.isRequestSolved = entity.getIsRequestSolved();
        this.meetingType = entity.getMeetingType();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.date = entity.getDate().toString();
        this.reward = entity.getReward();
        this.keyword = entity.getKeyword();
    }
}