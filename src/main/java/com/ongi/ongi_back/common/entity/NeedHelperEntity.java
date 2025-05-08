package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;

import com.ongi.ongi_back.common.dto.request.needHelper.PatchHelperPostRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "NeedHelperEntity")
@Table(name = "need_helper")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeedHelperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sequence;
    private String userId;
    private String reward;
    private String title;
    private String content;
    private String schedule;
    private Boolean isRequestSolved;
    private String meetingType;
    private String city;
    private String district;
    private LocalDateTime date;
    private Integer liked;

    public NeedHelperEntity(PostHelperRequestDto dto, String userId) {
        this.userId = userId;
        this.reward = dto.getReward();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.schedule = dto.getSchedule();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.meetingType = dto.getMeetingType(); 
        this.date = LocalDateTime.now();
        this.isRequestSolved = false;
        this.liked = 0;
    }

    public void patch(PatchHelperPostRequestDto dto) {
        this.reward = dto.getReward();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.schedule = dto.getSchedule();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.meetingType = dto.getMeetingType(); 
    }
}