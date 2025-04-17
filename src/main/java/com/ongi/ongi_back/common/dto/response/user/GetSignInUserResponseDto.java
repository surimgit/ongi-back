package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto{
  private String userId;
  private String nickname;
  private String userPassword;
  private String address;
  private String detailAddress;
  private Integer zipCode;
  private String birth;
  private String telNumber;
  private Boolean isSeller;
  private String gender;
  private String profileImage;
  private String mbti;
  private String job;
  private String selfIntro;
  private Integer userPoint;

  private GetSignInUserResponseDto(String nickname, String birth, String gender, String mbti,
  String job, String profileImage, String selfIntro){
    this.nickname = nickname;
    this.birth = birth;
    this.gender = gender;
    this.mbti = mbti;
    this.job = job;
    this.profileImage = profileImage;
    this.selfIntro = selfIntro;
  }

  private GetSignInUserResponseDto(String userId, String userPassword, String telNumber, String address,
  String detailAddress){
    this.userId = userId;
    this.userPassword = userPassword;
    this.telNumber = telNumber;
    this.address = address;
    this.detailAddress = detailAddress;
  }

  public static ResponseEntity<GetSignInUserResponseDto> userProfileResponseSuccess(UserEntity userEntity){
    GetSignInUserResponseDto body = new GetSignInUserResponseDto(
      userEntity.getNickname(), 
      userEntity.getBirth(), 
      userEntity.getGender(), 
      userEntity.getMbti(), 
      userEntity.getJob(),
      userEntity.getProfileImage(),
      userEntity.getSelfIntro());
    
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

  public static ResponseEntity<GetSignInUserResponseDto> userSettingResponseSuccess(UserEntity userEntity){
    GetSignInUserResponseDto body = new GetSignInUserResponseDto(
      userEntity.getUserId(),
      userEntity.getUserPassword(),
      userEntity.getTelNumber(),
      userEntity.getAddress(),
      userEntity.getDetailAddress());

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
