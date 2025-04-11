package com.ongi.ongi_back.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    
    @Id
    private String userId;
    private String nickname;
    private String userPassword;
    private String name;
    private String address;
    private String detailAddress;
    private Integer zipCode;
    private String birth;
    private String telNum;
    private Boolean isSeller;
    private String gender;
    private String profileImg;
    private String mbti;
    private String likeKeyword;
    private String job;
    private String selfIntro;
    private String badges;
    private Integer userPoint;
    private Boolean isAdmin;
    private Boolean isResigned;

}
