package com.ongi.ongi_back.common.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.LikeKeywordVO;

import lombok.Getter;

@Getter
public class GetProfileResponseDto extends ResponseDto {
  private List<LikeKeywordVO> likeKeywords;

  private String nickname;
  private String birth;
  private String gender;
  private String profileImage;
  private String mbti;
  private String job;
  private String selfIntro;

  private GetProfileResponseDto(UserEntity userEntity, List<LikeKeywordEntity> likeKeywordEntities){
    this.nickname = userEntity.getNickname();
    this.birth = userEntity.getBirth();
    this.gender = userEntity.getGender();
    this.profileImage = userEntity.getProfileImage();
    this.mbti = userEntity.getMbti();
    this.job = userEntity.getJob();
    this.selfIntro = userEntity.getSelfIntro();
    this.likeKeywords = LikeKeywordVO.getLikeKeywordList(likeKeywordEntities);
  }

  public static ResponseEntity<GetProfileResponseDto> success(UserEntity userEntity, List<LikeKeywordEntity> likeKeywordEntities){
    GetProfileResponseDto body = new GetProfileResponseDto(userEntity, likeKeywordEntities);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

}
