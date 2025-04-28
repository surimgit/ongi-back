package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAccountRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.CommunityCommentRepository;
import com.ongi.ongi_back.repository.CommunityPostRepository;
import com.ongi.ongi_back.repository.LikeKeywordRepository;
import com.ongi.ongi_back.repository.LikedRepository;
import com.ongi.ongi_back.repository.ProductRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.MypageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImplement implements MypageService{
  
  private final LikeKeywordRepository likeKeywordRepository;
  private final UserRepository userRepository;
  private final CommunityPostRepository communityPostRepository;
  private final CommunityCommentRepository communityCommentRepository;
  private final LikedRepository likedRepository;
  private final ProductRepository productRepository;

  @Override
  public ResponseEntity<ResponseDto> patchIntroduction(PatchUserIntroductionRequestDto dto, String userId) {
    
    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      if(userEntity == null) return ResponseDto.noExistUser();
      userEntity.patchIntroduction(dto);
      if(dto.getProfileImage() != null && !dto.getProfileImage().isBlank()){
        userEntity.setProfileImage(dto.getProfileImage());
      }
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
      if(likeKeywordEntities == null | userEntity == null) return ResponseDto.noExistUser();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return GetUserIntroductionResponseDto.success(userEntity, likeKeywordEntities);
  }

  @Override
  public ResponseEntity<? super GetCommunityResponseDto> getMyCommunityPost(String userId) {
    
    List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);
      String nickname = userEntity.getNickname();
      communityPostEntities = communityPostRepository.findByNicknameOrderByPostSequenceDesc(nickname);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return GetCommunityResponseDto.success(communityPostEntities);
  }

  @Override
  public ResponseEntity<? super GetCommunityCommentsResponseDto> getMyCommunityComment(String userId) {
    
    List<CommunityCommentEntity> communityCommentEntities = new ArrayList<>();
    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByUserId(userId);
      String nickname = userEntity.getNickname();
      communityCommentEntities = communityCommentRepository.findByNicknameOrderByCommentSequenceDesc(nickname);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return GetCommunityCommentsResponseDto.success(communityCommentEntities);
  }

  @Override
  public ResponseEntity<? super GetCommunityResponseDto> getMyCommunityLikedPostComment(String userId) {
      List<LikedEntity> likedEntities = new ArrayList<>();
      List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
    try {
      likedEntities = likedRepository.findByUserId(userId);
      List<Integer> postSequenceList = likedEntities.stream()
        .map(LikedEntity::getLikedPostSequence)
        .collect(Collectors.toList());

      communityPostEntities = communityPostRepository.findAllByPostSequenceInOrderByPostSequenceDesc(postSequenceList);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return GetCommunityResponseDto.success(communityPostEntities);
  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getMyPurchaseList(String userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMyPurchaseList'");
  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getMySelledList(String userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMySelledList'");
  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getMyWishList(String userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMyWishList'");
  }

  
}