package com.ongi.ongi_back.common.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class FindIdResponseDto extends ResponseDto{

    private String userId;

    private FindIdResponseDto(String userId) {
        this.userId = userId;
    }

    public static ResponseEntity<? super ResponseDto> success(String userId){
        FindIdResponseDto body = new FindIdResponseDto(userId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
