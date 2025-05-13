package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserProfileImageResponseDto extends ResponseDto {
    private String profileImage;

    private GetUserProfileImageResponseDto(UserEntity userEntity) {
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetUserProfileImageResponseDto> success(UserEntity userEntity) {
        GetUserProfileImageResponseDto body = new GetUserProfileImageResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
