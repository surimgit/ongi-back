package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAccountRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.LikeKeywordRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{
  private final UserRepository userRepository;  
  private final LikeKeywordRepository likeKeywordRepository;

  @Override
  public ResponseEntity<ResponseDto> patchIntroduction(PatchUserIntroductionRequestDto dto, String userId) {
    
    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      if(userEntity == null) return ResponseDto.noExistUser();

      userEntity.patchIntroduction(dto);
      userRepository.save(userEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

    @Override
    public ResponseEntity<ResponseDto> patchUserAccount(PatchUserAccountRequestDto dto, String userId) {

      try {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) return ResponseDto.noExistUser();

        userEntity.patchUserAccount(dto);
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
  public ResponseEntity<? super GetUserAccountResponseDto> getUserAccount(String userId) {
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserAccountResponseDto.success(userEntity);
  }

  @Override
  public ResponseEntity<? super GetLikeKeywordListResponseDto> getLikeKeywordList(String userId) {
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
  public ResponseEntity<? super GetUserIntroductionResponseDto> getUserIntroduction(String userId) {

    List<LikeKeywordEntity> likeKeywordEntities = new ArrayList<>();
    UserEntity userEntity = null;

    try {
      likeKeywordEntities = likeKeywordRepository.findAllByUserId(userId);
      userEntity = userRepository.findByUserId(userId);
    
    } catch (Exception e) {
      e.printStackTrace();
    }

    return GetUserIntroductionResponseDto.success(userEntity, likeKeywordEntities);
  }
  
}
