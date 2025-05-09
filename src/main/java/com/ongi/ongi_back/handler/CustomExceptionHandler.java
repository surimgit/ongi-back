package com.ongi.ongi_back.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    
    // description: MethodArgumentNotValidException - 유효성 검사 실패 //
    // description: HttpMessageNotReadableException - RequestBody가 필요한데 존재하지 않을 때 발생 //
    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ResponseDto> validExceptionHandler(Exception exception) {
        log.error("유효성 검사 실패: {}", exception.getMessage());
        return ResponseDto.validationFail();
    }

}
