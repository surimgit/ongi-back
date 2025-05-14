package com.ongi.ongi_back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ongi.ongi_back.common.dto.request.event.PostEventApplyRequestDto;
import com.ongi.ongi_back.common.dto.request.event.PostEventRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.event.GetEventListResponseDto;
import com.ongi.ongi_back.common.entity.EventApplyEntity;
import com.ongi.ongi_back.common.entity.EventEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.EventApplyRepository;
import com.ongi.ongi_back.repository.EventRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImplement implements EventService {

    private final EventRepository eventRepository;
    private final EventApplyRepository eventApplyRepository;
    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postEvent(PostEventRequestDto dto, String userId) {

        EventEntity eventEntity = null;
        UserEntity userEntity = null;
        
        try {

            userEntity = userRepository.findByUserId(userId);
            if (!userEntity.getIsAdmin()) return ResponseDto.noPermission();

            eventEntity = new EventEntity(dto);
            eventRepository.save(eventEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super GetEventListResponseDto> getEventList() {

        List<EventEntity> eventEntities = null;
        
        try {

            eventEntities = eventRepository.findActiveEvents();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetEventListResponseDto.success(eventEntities);
    }

    @Override
    @Scheduled(cron = " 0 0 0 * * ? ")
    @Transactional
    public void closeExpiredEvents() {

        List<EventEntity> eventEntities = eventRepository.findClosingEvents();
        if (eventEntities == null) return;

        for (EventEntity eventEntity: eventEntities) {
            eventEntity.setClosed(true);
        }

        eventRepository.saveAll(eventEntities);

        System.out.println(eventEntities.size() + " events has been closed.");
        return;
    }

    @Override
    public ResponseEntity<ResponseDto> postEventApply(PostEventApplyRequestDto dto, String userId) {

        EventApplyEntity eventApplyEntity = null;
        EventEntity eventEntity = null;
        UserEntity userEntity = null;
        
        try {

            eventApplyEntity = new EventApplyEntity(dto);

            userEntity = userRepository.findByUserId(userId);
            eventEntity = eventRepository.findByEventSequence(eventApplyEntity.getAppliedEventSequence());
            
            Integer userPoint = userEntity.getUserPoint();
            Integer eventPoint = eventEntity.getNeededPoint();

            if (userPoint < eventPoint) return ResponseDto.notEnoughPoint();

            userEntity.setUserPoint(userPoint - eventPoint);
            userRepository.save(userEntity);

            eventApplyRepository.save(eventApplyEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }
    
}
