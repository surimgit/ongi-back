package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAccountRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostReviewImagesRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostWaybillRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMySalesResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetOrderItemResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.ProductReviewEntity;
import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.MyBuyingVO;
import com.ongi.ongi_back.common.vo.MySalesVO;
import com.ongi.ongi_back.common.vo.OrderItemVO;
import com.ongi.ongi_back.repository.CommunityCommentRepository;
import com.ongi.ongi_back.repository.CommunityPostRepository;
import com.ongi.ongi_back.repository.LikeKeywordRepository;
import com.ongi.ongi_back.repository.LikedRepository;
import com.ongi.ongi_back.repository.OrderItemRepository;
import com.ongi.ongi_back.repository.ProductRepository;
import com.ongi.ongi_back.repository.ProductReviewRepository;
import com.ongi.ongi_back.repository.ReviewImagesRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.FileService;
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
  private final ProductReviewRepository productReviewRepository;
  private final OrderItemRepository orderItemRepository;
  private final ReviewImagesRepository reviewImagesRepository;
  
  private final FileService fileService;

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
  public ResponseEntity<? super GetMyBuyingResponseDto> getMyPurchaseList(String userId) {

    List<MyBuyingVO> list = new ArrayList<>();

    try{
      
      list = orderItemRepository.findMyBuyingList(userId);
      
    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyBuyingResponseDto.success(list);
  }

  @Override
  public ResponseEntity<ResponseDto> postProductReview(PostProductReviewRequestDto dto, String userId) {
    
    try {

      Integer productSequence = dto.getProductSequence();

      ProductReviewEntity productReviewEntity = productReviewRepository.findByUserIdAndProductSequence(userId, productSequence);
      if(productReviewEntity != null) return ResponseDto.alreadyPostReview();

      productReviewEntity = new ProductReviewEntity(dto, userId);

      productReviewRepository.save(productReviewEntity);

      Integer reviewSequence = productReviewEntity.getReviewSequence();

      String[] reviewImages = dto.getReviewImages();

      if(reviewImages != null) {
        for(int i = 0; i < reviewImages.length; i++){
          if(reviewImages[i] != null) {
            PostReviewImagesRequestDto imageDto = new PostReviewImagesRequestDto(reviewSequence, i, reviewImages[i]); 
            ReviewImagesEntity reviewImagesEntity = new ReviewImagesEntity(imageDto);
            reviewImagesRepository.save(reviewImagesEntity);
          }
        }
      }
      
    } catch(Exception exception){
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetMySalesResponseDto> getMySalesList(String userId) {
    
    List<MySalesVO> mySales = new ArrayList<>();

    try {

      List<ProductEntity> productEntities = productRepository.findByUserId(userId);
      
      for(ProductEntity productEntity: productEntities){
        MySalesVO mySalesVO = new MySalesVO(productEntity);
        mySales.add(mySalesVO);
      }

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMySalesResponseDto.success(mySales);
  }

  @Override
  public ResponseEntity<? super GetOrderItemResponseDto> getOrderItemByProductSequence(Integer sequence) {
    List<OrderItemVO> orderItems = new ArrayList<>();

    try{

      orderItems = orderItemRepository.findByProductSequence(sequence);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return GetOrderItemResponseDto.success(orderItems);
  }

  @Override
  public ResponseEntity<ResponseDto> postWaybillNumber(PostWaybillRequestDto dto, String userId) {

    try{
      Integer sequence = dto.getOrderItemSequence();
      String waybillNumber = dto.getWaybillNumber();

      OrderItemEntity orderItemEntity = orderItemRepository.findByOrderItemSequence(sequence);

      if(orderItemEntity == null) return ResponseDto.validationFail();
      orderItemEntity.setWaybillNumber(waybillNumber);

      orderItemRepository.save(orderItemEntity);
      
    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  
}