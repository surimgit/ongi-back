package com.ongi.ongi_back.common.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserNicknameResponseDto extends ResponseDto {
    private String nickname;

    private GetUserNicknameResponseDto(UserEntity userEntity) {
        this.nickname = userEntity.getNickname();
    }

    public static ResponseEntity<GetUserNicknameResponseDto> success(UserEntity userEntity) {
        GetUserNicknameResponseDto body = new GetUserNicknameResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
