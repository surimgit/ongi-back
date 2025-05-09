package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {
    private String userId;
    private String nickname;
    private Boolean isAdmin;
    private String profileImage;

    private GetSignInUserResponseDto(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.nickname = userEntity.getNickname();
        this.isAdmin = userEntity.getIsAdmin();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity) {
        GetSignInUserResponseDto body = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
