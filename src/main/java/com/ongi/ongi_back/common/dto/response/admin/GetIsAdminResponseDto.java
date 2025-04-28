package com.ongi.ongi_back.common.dto.response.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetIsAdminResponseDto extends ResponseDto {
    private Boolean isAdmin;

    private GetIsAdminResponseDto(UserEntity userEntity) {
        this.isAdmin = userEntity.getIsAdmin();
    }

    public static ResponseEntity<GetIsAdminResponseDto> success(UserEntity userEntity) {
        GetIsAdminResponseDto body = new GetIsAdminResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
