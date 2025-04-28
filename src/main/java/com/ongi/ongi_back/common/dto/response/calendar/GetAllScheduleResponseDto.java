package com.ongi.ongi_back.common.dto.response.calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.entity.CalendarEntity;
import com.ongi.ongi_back.common.vo.CalendarVO;

import java.util.List;

import lombok.Getter;

@Getter
public class GetAllScheduleResponseDto {
   
    private List<CalendarVO> schedules;

    private GetAllScheduleResponseDto(List<CalendarEntity> calendarEntities){
        this.schedules = CalendarVO.getList(calendarEntities);
    }

    public static ResponseEntity<GetAllScheduleResponseDto> success(List<CalendarEntity> calendarEntities) {
        GetAllScheduleResponseDto body = new GetAllScheduleResponseDto(calendarEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
