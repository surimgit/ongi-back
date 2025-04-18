package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.alert.PostAlertRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.alert.GetAlertResponseDto;
import com.ongi.ongi_back.common.entity.AlertEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.AlertRespository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.AlertService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertServiceimplement implements AlertService {

    private final AlertRespository alertRespository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postAlert(PostAlertRequestDto dto) {
        
        try {

            AlertEntity alertEntity = new AlertEntity(dto);
            UserEntity senderEntity = userRepository.findByUserId(alertEntity.getSenderId());
            String senderNickname = senderEntity.getNickname();

            if (alertEntity.getAlertType().equals("community_comment")) {
                alertEntity.setAlertContent(senderNickname + " 님이 댓글을 달았습니다.");
            }

            alertRespository.save(alertEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super GetAlertResponseDto> getAlert(String userId) {

        List<AlertEntity> alertEntities = new ArrayList<>();
        
        try {

            alertEntities = alertRespository.findByReceiverId(userId);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetAlertResponseDto.success(alertEntities);
    }
    
}
