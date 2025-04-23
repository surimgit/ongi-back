package com.ongi.ongi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ongi.ongi_back.common.dto.request.calendar.PatchCalendarRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetAllScheduleResponseDto;
import com.ongi.ongi_back.common.dto.request.calendar.PostScheduleRequestDto;
import com.ongi.ongi_back.common.entity.CalendarEntity;
import com.ongi.ongi_back.repository.CalendarRepository;
import com.ongi.ongi_back.service.CalendarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarServiceImplement implements CalendarService {

    private final CalendarRepository calendarRepository;

    @Override
    public ResponseEntity<? super GetAllScheduleResponseDto> getAllSchedule(String userId) {
        
        List<CalendarEntity> calendarEntities = new ArrayList<>();
        try {
            calendarEntities = calendarRepository.findByUserId(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }
        return GetAllScheduleResponseDto.success(calendarEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> postSchedule(PostScheduleRequestDto dto, String userId) {
        try {
            CalendarEntity calendarEntity = new CalendarEntity(dto, userId);
            calendarRepository.save(calendarEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> patchSchedule(PatchCalendarRequestDto dto, Integer calendarSequence, String userId) {
        try {
            CalendarEntity calendarEntity = calendarRepository.findByCalendarSequence(calendarSequence);
            if(calendarEntity == null) return ResponseDto.noExistSchedule();

            calendarEntity.patch(dto);            
            calendarRepository.save(calendarEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteSchedule(Integer calendarSequence) {
        
        try {
            CalendarEntity calendarEntity = calendarRepository.findByCalendarSequence(calendarSequence);
            if (calendarEntity == null) {
                return ResponseDto.noExistSchedule();
            }
    
            calendarRepository.delete(calendarEntity);        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }
}
