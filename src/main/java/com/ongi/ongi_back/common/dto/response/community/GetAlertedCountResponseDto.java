package com.ongi.ongi_back.common.dto.response.community;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetAlertedCountResponseDto extends ResponseDto {
    private Integer alertedCount;

    private GetAlertedCountResponseDto(Integer alertedCount) {
        this.alertedCount = alertedCount;
    }

    public static ResponseEntity<GetAlertedCountResponseDto> success(Integer alertedCount) {
        GetAlertedCountResponseDto body = new GetAlertedCountResponseDto(alertedCount);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
