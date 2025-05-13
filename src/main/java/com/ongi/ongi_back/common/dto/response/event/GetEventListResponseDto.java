package com.ongi.ongi_back.common.dto.response.event;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.EventEntity;
import com.ongi.ongi_back.common.vo.EventVO;

import lombok.Getter;

@Getter
public class GetEventListResponseDto extends ResponseDto {
    private List<EventVO> events;

    private GetEventListResponseDto(List<EventEntity> eventEntities) {
        this.events = EventVO.getList(eventEntities);
    }

    public static ResponseEntity<GetEventListResponseDto> success(List<EventEntity> eventEntities) {
        GetEventListResponseDto body = new GetEventListResponseDto(eventEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
