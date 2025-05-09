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
            
            UserEntity receiverEntity = userRepository.findByUserId(alertEntity.getReceiverId());
            if (receiverEntity.getIsResigned()) return ResponseDto.alreadyResigned();

            if (alertEntity.getAlertType().equals("community_comment")) {
                alertEntity.setAlertContent(senderNickname + " 님이 댓글을 달았습니다.");
            }
            else if (alertEntity.getAlertType().equals("helper_comment")) {
                alertEntity.setAlertContent(senderNickname + " 님이 댓글을 달았습니다.");
            }
            else if (alertEntity.getAlertType().equals("report_alerted")) {
                alertEntity.setAlertContent("신고가 접수되어 경고를 받았습니다.(사유:"+alertEntity.getReason()+")");
            }
            else if (alertEntity.getAlertType().equals("wish_open")){
                alertEntity.setAlertContent("찜해놓은 상품이 오픈했습니다!");
            }
            else if (alertEntity.getAlertType().equals("waybill")){
                alertEntity.setAlertContent("주문하신 상품을 판매자가 배송 시작했습니다!");
            }
            else if (alertEntity.getAlertType().equals("payment_cancel")){
                alertEntity.setAlertContent("선택하신 상품의 결제가 취소되었습니다!");
            }
            else if (alertEntity.getAlertType().equals("helper_apply")) {
                alertEntity.setAlertContent(senderNickname + " 님이 도우미 신청 요청을 보냈습니다.");
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

            alertEntities = alertRespository.findByReceiverIdOrderByAlertSequenceDesc(userId);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetAlertResponseDto.success(alertEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> patchAlertRead(Integer alertSequence) {
        
        try {

            AlertEntity alertEntity = alertRespository.findByAlertSequence(alertSequence);
            alertEntity.setReadPara(true);
            alertRespository.save(alertEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAlert(Integer alertSequence, String userId) {
        
        try {

            if (alertSequence == null) {
                alertRespository.deleteByReceiverId(userId);
            }
            else {
                AlertEntity alertEntity = alertRespository.findByAlertSequence(alertSequence);
                
                String receiverId = alertEntity.getReceiverId();
                boolean isReceiver = receiverId.equals(userId);
                if (!isReceiver) return ResponseDto.noPermission();

                alertRespository.deleteByAlertSequence(alertSequence);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }
    
}
