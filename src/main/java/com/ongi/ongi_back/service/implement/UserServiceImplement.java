package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserRequestDto;
import com.ongi.ongi_back.common.dto.request.user.UpdateIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetProfileResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.LikeKeywordRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.UserService;

import lombok.RequiredArgsConstructor;
import retrofit2.http.HTTP;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{
  private final UserRepository userRepository;  
  private final LikeKeywordRepository likeKeywordRepository;

  @Override
  public ResponseEntity<ResponseDto> updateIntroduction(UpdateIntroductionRequestDto dto, String userId) {
    
    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      if(userEntity == null) return ResponseDto.noExistUser();

      userEntity.updateIntroduction(dto);
      userRepository.save(userEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

    @Override
    public ResponseEntity<ResponseDto> patchUser(PatchUserRequestDto dto, String userId) {

      try {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) return ResponseDto.noExistUser();

        userEntity.patchUserInformation(dto);
        userRepository.save(userEntity);
      } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.databaseError();
      }

      return ResponseDto.success(HttpStatus.OK);
    }

  @Override
  public ResponseEntity<ResponseDto> addLikeKeyword(AddLikeKeywordRequestDto dto, String userId) {
    
    try {
      LikeKeywordEntity exists = likeKeywordRepository.findByUserIdAndKeyword(userId, dto.getKeyword());
      if(exists != null) return ResponseDto.alreadyApplied();

      LikeKeywordEntity likeKeywordEntity = new LikeKeywordEntity(dto, userId);
      likeKeywordRepository.save(likeKeywordEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> deleteLikeKeyword(DeleteLikeKeywordRequestDto dto, String userId) {
    try {
      LikeKeywordEntity likeKeywordEntity = likeKeywordRepository.findByUserIdAndKeyword(userId, dto.getKeyword());
      if(likeKeywordEntity == null) return ResponseDto.alreadyApplied();

      likeKeywordRepository.delete(likeKeywordEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getUserProfileInformation(String userId) {
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDto.userProfileResponseSuccess(userEntity);
  }

  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getUserSetting(String userId) {
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDto.userSettingResponseSuccess(userEntity);
  }

  @Override
  public ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeyword(String userId) {
    List<LikeKeywordEntity> likeKeywordEntities = new ArrayList<>();

    try {
      likeKeywordEntities = likeKeywordRepository.findAllByUserId(userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetLikeKeywordListResponseDto.success(likeKeywordEntities);
  }

  @Override
  public ResponseEntity<? super GetProfileResponseDto> getUserProfile(String userId) {

    List<LikeKeywordEntity> likeKeywordEntities = new ArrayList<>();
    UserEntity userEntity = null;

    try {
      likeKeywordEntities = likeKeywordRepository.findAllByUserId(userId);
      userEntity = userRepository.findByUserId(userId);
    

    } catch (Exception e) {
      e.printStackTrace();
    }

    return GetProfileResponseDto.success(userEntity, likeKeywordEntities);
  }
  
}
