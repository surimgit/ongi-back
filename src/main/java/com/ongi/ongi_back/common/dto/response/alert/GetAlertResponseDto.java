package com.ongi.ongi_back.common.dto.response.alert;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.AlertEntity;
import com.ongi.ongi_back.common.vo.AlertVO;

import lombok.Getter;

@Getter
public class GetAlertResponseDto extends ResponseDto {
    private List<AlertVO> alerts;

    private GetAlertResponseDto(List<AlertEntity> alertEntities) {
        this.alerts = AlertVO.getList(alertEntities);
    }

    public static ResponseEntity<GetAlertResponseDto> success(List<AlertEntity> alertEntities) {
        GetAlertResponseDto body = new GetAlertResponseDto(alertEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
