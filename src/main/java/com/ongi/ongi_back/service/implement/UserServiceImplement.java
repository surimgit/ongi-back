package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.user.PatchResignRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.admin.GetIsAdminResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserNicknameResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserProfileImageResponseDto;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
        
        UserEntity userEntity = null;

        try {
            
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUser();
            if (userEntity.getIsResigned()) return ResponseDto.alreadyResigned();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserNicknameResponseDto> getUserNickname(String userId) {

        UserEntity userEntity = null;
        
        try {

            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserNicknameResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> resignUser(PatchResignRequestDto dto, String userId) {

        UserEntity userEntity = null;
        
        try {

            userEntity = userRepository.findByUserId(dto.getUserId());
            if (userEntity == null) return ResponseDto.noExistUser();
            if (userEntity.getIsResigned()) return ResponseDto.alreadyResigned();
            if (!userEntity.getUserId().equals(userId) && !dto.getIsAdmin()) return ResponseDto.noPermission();
            String resignedTelNum = "탈퇴" + userRepository.countAllByIsResigned(true).toString();

            userEntity.setUserPassword("");
            userEntity.setAddress(null);
            userEntity.setDetailAddress(null);
            userEntity.setZipCode(null);
            userEntity.setBirth(null);
            userEntity.setTelNumber(resignedTelNum);
            userEntity.setIsSeller(false);
            userEntity.setGender("");
            userEntity.setProfileImage(null);
            userEntity.setMbti(null);
            userEntity.setJob(null);
            userEntity.setSelfIntro(dto.getReason());
            userEntity.setUserPoint(null);
            userEntity.setIsAdmin(false);
            userEntity.setIsResigned(true);

            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<? super GetIsAdminResponseDto> getIsAdmin(String userId) {

        UserEntity userEntity = null;
        
        try {

        userEntity = userRepository.findByIsAdminTrue();
        if (userEntity.getIsResigned()) return ResponseDto.alreadyResigned();
        
        } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
        }

        return GetIsAdminResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserProfileImageResponseDto> getUserProfileImage(String userId) {

        UserEntity userEntity = null;
        
        try {

            userEntity = userRepository.findByUserId(userId);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserProfileImageResponseDto.success(userEntity);
    }
}
