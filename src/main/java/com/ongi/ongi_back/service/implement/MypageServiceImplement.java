package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.user.AddLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.DeleteLikeKeywordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchBadgeRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserPasswordRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostReviewImagesRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostWaybillRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.badge.GetBadgeListResponseDto;
import com.ongi.ongi_back.common.dto.response.badge.GetBadgeResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperApplyListRespeonseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetMyHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetLikeKeywordListResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyActivityCountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetMySalesResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetOrderItemResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserAccountResponseDto;
import com.ongi.ongi_back.common.dto.response.user.GetUserIntroductionResponseDto;
import com.ongi.ongi_back.common.entity.BadgeEntity;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.HelperApplyEntity;
import com.ongi.ongi_back.common.entity.HelperLikedEntity;
import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.ProductReviewEntity;
import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.MyBuyingVO;
import com.ongi.ongi_back.common.vo.MySalesVO;
import com.ongi.ongi_back.common.vo.OrderItemVO;
import com.ongi.ongi_back.common.vo.ProductVO;
import com.ongi.ongi_back.repository.*;
import com.ongi.ongi_back.service.MypageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImplement implements MypageService {

  private final HelperCommentRepository helperCommentRepository;

  private final LikeKeywordRepository likeKeywordRepository;
  private final UserRepository userRepository;
  private final CommunityPostRepository communityPostRepository;
  private final CommunityCommentRepository communityCommentRepository;
  private final LikedRepository likedRepository;
  private final ProductReviewRepository productReviewRepository;
  private final ProductRepository productRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final WishListRepository wishListRepository;
  private final OrderItemRepository orderItemRepository;
  private final ReviewImagesRepository reviewImagesRepository;
  private final BadgeRepository badgeRepository;
  private final HelperPostRepository helperPostRepository;
  private final HelperApplyRepository helperApplyRepository;
  private final HelperLikedRepository helperLikedRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ResponseEntity<ResponseDto> patchIntroduction(PatchUserIntroductionRequestDto dto, String userId) {

    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      if (userEntity == null)
        return ResponseDto.noExistUser();
      userEntity.patchIntroduction(dto);
      if (dto.getProfileImage() != null && !dto.getProfileImage().isBlank()) {
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
  public ResponseEntity<ResponseDto> patchUserPassword(PatchUserPasswordRequestDto dto, String userId) {

    try {

      UserEntity userEntity = userRepository.findByUserId(userId);
      if (userEntity == null)
        return ResponseDto.noExistUser();

      boolean isPasswordVaild = passwordEncoder.matches(dto.getCurrentPassword(), userEntity.getUserPassword());
      if (!isPasswordVaild)
        return ResponseDto.noPermission();
      boolean equalCheck = dto.getCurrentPassword().equals(dto.getNewPassword());
      if (equalCheck)
        return ResponseDto.invalidRequest();

      String encoded = passwordEncoder.encode(dto.getNewPassword());
      dto.setNewPassword(encoded);
      userEntity.patchUserPassword(dto);
      userRepository.save(userEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchUserAddress(PatchUserAddressRequestDto dto, String userId) {

    try {
      UserEntity userEntity = userRepository.findByUserId(userId);
      if (userEntity == null)
        return ResponseDto.noExistUser();

      userEntity.patchUserAddress(dto);
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
      if (exists != null)
        return ResponseDto.alreadyApplied();

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
      if (likeKeywordEntity == null)
        return ResponseDto.alreadyApplied();

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
      if (likeKeywordEntities == null | userEntity == null)
        return ResponseDto.noExistUser();

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

    try {

      list = orderItemRepository.findMyBuyingList(userId);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyBuyingResponseDto.success(list);
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

  @Override
  public ResponseEntity<ResponseDto> postProductReview(PostProductReviewRequestDto dto, String userId) {

    try {

      Integer productSequence = dto.getProductSequence();

      ProductReviewEntity productReviewEntity = productReviewRepository.findByUserIdAndProductSequence(userId,
          productSequence);
      if (productReviewEntity != null)
        return ResponseDto.alreadyPostReview();

      productReviewEntity = new ProductReviewEntity(dto, userId);

      productReviewRepository.save(productReviewEntity);

      Integer reviewSequence = productReviewEntity.getReviewSequence();

      String[] reviewImages = dto.getReviewImages();

      if (reviewImages != null) {
        for (int i = 0; i < reviewImages.length; i++) {
          if (reviewImages[i] != null) {
            PostReviewImagesRequestDto imageDto = new PostReviewImagesRequestDto(reviewSequence, i, reviewImages[i]);
            ReviewImagesEntity reviewImagesEntity = new ReviewImagesEntity(imageDto);
            reviewImagesRepository.save(reviewImagesEntity);
          }
        }
      }

    } catch (Exception exception) {
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

      for (ProductEntity productEntity : productEntities) {
        MySalesVO mySalesVO = new MySalesVO(productEntity);
        mySales.add(mySalesVO);
      }

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMySalesResponseDto.success(mySales);
  }

  @Override
  public ResponseEntity<? super GetOrderItemResponseDto> getOrderItemByProductSequence(Integer sequence) {
    List<OrderItemVO> orderItems = new ArrayList<>();

    try {

      orderItems = orderItemRepository.findByProductSequence(sequence);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetOrderItemResponseDto.success(orderItems);
  }

  @Override
  public ResponseEntity<ResponseDto> postWaybillNumber(PostWaybillRequestDto dto, String userId) {

    try {
      Integer sequence = dto.getOrderItemSequence();
      String waybillNumber = dto.getWaybillNumber();

      OrderItemEntity orderItemEntity = orderItemRepository.findByOrderItemSequence(sequence);

      if (orderItemEntity == null)
        return ResponseDto.validationFail();
      orderItemEntity.setWaybillNumber(waybillNumber);

      orderItemRepository.save(orderItemEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  public ResponseEntity<ResponseDto> addBadge(String userId) {

    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null) {
      return ResponseDto.noExistUser();
    }
    String nickname = userEntity.getNickname();

    try {
      List<LikeKeywordEntity> likeKeywordEntities = likeKeywordRepository.findAllByUserId(userId);
      Boolean keyword = likeKeywordEntities != null;

      if (userEntity.getBirth() != null && userEntity.getGender() != null && userEntity.getMbti() != null
          && userEntity.getJob() != null && userEntity.getSelfIntro() != null && keyword) {
        if (badgeRepository.findByUserIdAndBadge(userId, "자기소개 작성 완료!") == null) {
          BadgeEntity badgeEntity = new BadgeEntity(userId, "자기소개 작성 완료!", false);
          badgeRepository.save(badgeEntity);
        }
      }

      if (communityPostRepository.findByNicknameOrderByPostSequenceDesc(nickname).size() >= 10) {
        if (badgeRepository.findByUserIdAndBadge(userId, "게시글 10개 작성!") == null) {
          BadgeEntity badgeEntity = new BadgeEntity(userId, "게시글 10개 작성!", false);
          badgeRepository.save(badgeEntity);
        }
      }

      if (communityCommentRepository.findByNicknameOrderByCommentSequenceDesc(nickname).size() >= 10) {
        if (badgeRepository.findByUserIdAndBadge(userId, "댓글 10개 작성!") == null) {
          BadgeEntity badgeEntity = new BadgeEntity(userId, "댓글 10개 작성!", false);
          badgeRepository.save(badgeEntity);
        }
      }

    } catch (DataIntegrityViolationException e) {
    }
    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetBadgeListResponseDto> getBadgeList(String userId) {
    List<BadgeEntity> badgeEntities = new ArrayList<>();

    try {
      badgeEntities = badgeRepository.findAllByUserId(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetBadgeListResponseDto.success(badgeEntities);

  }

  @Override
  public ResponseEntity<ResponseDto> chooseBadge(String userId, PatchBadgeRequestDto dto) {
    BadgeEntity selectBadgeEntity = null;
    try {
      selectBadgeEntity = badgeRepository.findByUserIdAndBadge(userId, dto.getBadge());
      if (selectBadgeEntity == null)
        return ResponseDto.invalidRequest();

      BadgeEntity beforeBadge = null;
      List<BadgeEntity> badgeEntities = new ArrayList<>();
      badgeEntities = badgeRepository.findAllByUserId(userId);
      for (BadgeEntity badge : badgeEntities) {
        if (badge.getIsSelected()) {
          beforeBadge = badge;
        }
      }

      if (beforeBadge != null && !selectBadgeEntity.getBadge().equals(beforeBadge.getBadge())) {
        beforeBadge.setIsSelected(false);
        selectBadgeEntity.patchBadge(dto, userId);
        badgeRepository.save(selectBadgeEntity);
      }
      selectBadgeEntity.patchBadge(dto, userId);
      badgeRepository.save(selectBadgeEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<? super GetBadgeResponseDto> getOtherUserBadge(String userId) {
    List<BadgeEntity> badgeEntities = new ArrayList<>();
    badgeEntities = badgeRepository.findAllByUserId(userId);
    BadgeEntity badgeEntity = null;

    try {
      for (BadgeEntity badge : badgeEntities) {
        if (badge.getIsSelected()) {
          badgeEntity = badge;
        }
      }
      if (badgeEntity == null) {
        return ResponseDto.success(HttpStatus.OK);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetBadgeResponseDto.success(badgeEntity);
  }

  @Override
  public ResponseEntity<? super GetUserIntroductionResponseDto> getOtherUserIntroduction(String userId) {

    List<LikeKeywordEntity> likeKeywordEntities = new ArrayList<>();
    UserEntity userEntity = userRepository.findByUserId(userId);

    try {
      likeKeywordEntities = likeKeywordRepository.findAllByUserId(userEntity.getUserId());
      if (likeKeywordEntities == null | userEntity == null)
        return ResponseDto.noExistUser();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserIntroductionResponseDto.success(userEntity, likeKeywordEntities);
  }

  @Override
  public ResponseEntity<? super GetMyActivityCountResponseDto> getMyActivityCount(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null)
      return ResponseDto.noExistUser();
    Integer communityCommentCount = 0;
    Integer communityPostCount = 0;
    Integer applyCount = 0;
    Integer acceptCount = 0;
    Integer shoppingCartCount = 0;
    Integer wishListCount = 0;

    try {
      applyCount = helperPostRepository.countByUserId(userId);
      acceptCount = helperApplyRepository.countByApplicantId(userId);
      communityCommentCount = communityCommentRepository.countByUserId(userId);
      communityPostCount = communityPostRepository.countByUserId(userId);
      shoppingCartCount = shoppingCartRepository.countByUserId(userId);
      wishListCount = wishListRepository.countByUserId(userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyActivityCountResponseDto.success(communityCommentCount, communityPostCount, applyCount, acceptCount,
        shoppingCartCount, wishListCount);
  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getOtherUserSellingProduct(String userId) {
    List<ProductVO> productEntities = new ArrayList<>();
    UserEntity userEntity = userRepository.findByUserId(userId);
    try {
      if (userEntity == null)
        return ResponseDto.noExistUser();
      productEntities = productRepository.findByStatus("OPEN");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetProductListResponseDto.success(productEntities, "all");
  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getOtherUserSelledProduct(String userId) {
    List<ProductVO> productEntities = new ArrayList<>();
    UserEntity userEntity = userRepository.findByUserId(userId);
    try {
      if (userEntity == null)
        return ResponseDto.noExistUser();
      productEntities = productRepository.findByStatus("CLOSE");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetProductListResponseDto.success(productEntities, "all");
  }

  @Override
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperRequestPost(String userId) {
    List<NeedHelperEntity> needHelperEntities = new ArrayList<>();

    try {
      needHelperEntities = helperPostRepository.findAllByUserIdOrderBySequenceDesc(userId);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyHelperPostListResponseDto.success(needHelperEntities);
  }

  @Override
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperApplyPost(String userId) {
    List<Integer> myApplyList = new ArrayList<>();
    List<NeedHelperEntity> needHelperEntities = new ArrayList<>();
    try {
      myApplyList = helperApplyRepository.findPostSequenceByApplicantId(userId);
      for (Integer postSequence : myApplyList) {
        NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(postSequence);
        if (needHelperEntity != null) {
          needHelperEntities.add(needHelperEntity);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyHelperPostListResponseDto.success(needHelperEntities);
  }

  @Override
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getMyHelperLikedPost(String userId) {
    List<HelperLikedEntity> myLikeList = new ArrayList<>();
    List<NeedHelperEntity> needHelperEntities = new ArrayList<>();

    try {
      myLikeList = helperLikedRepository.findByUserId(userId);
      for (HelperLikedEntity likedEntity : myLikeList) {
        Integer postSequence = likedEntity.getLikedPostSequence();
        NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(postSequence);
        if (needHelperEntity != null) {
          needHelperEntities.add(needHelperEntity);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetMyHelperPostListResponseDto.success(needHelperEntities);
  }

  @Override
  public Integer getApplicantCount(Integer postSequence, String userId) {
    return helperApplyRepository.countByPostSequence(postSequence);
  }

  @Override
  public ResponseEntity<? super GetHelperApplyListRespeonseDto> getHelperApplyList(String userId,
      Integer postSequence) {
    List<HelperApplyEntity> helperApplyEntities = new ArrayList<>();

    try {
      helperApplyEntities = helperApplyRepository.findByPostSequenceAndRequesterId(postSequence, userId);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetHelperApplyListRespeonseDto.success(helperApplyEntities);

  }

  @Override
  public ResponseEntity<? super GetCommunityCommentsResponseDto> getOtherUserCommunityComment(String userId) {

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
  public ResponseEntity<? super GetCommunityResponseDto> getOtherUserCommunityPost(String userId) {
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
  public ResponseEntity<? super GetMyHelperPostListResponseDto> getOtherUserHelperPost(String userId) {
    try {
      List<GetHelperPostResponseDto> posts = helperPostRepository.findAllWithNickname();
      return ResponseEntity.ok(new GetHelperPostListResponseDto(posts));
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
  }

  @Override
  public ResponseEntity<? super GetHelperCommentsResponseDto> getOtherUserHelperComments(Integer postSequence,
      String userId) {
    List<NeedHelperCommentEntity> helperCommentEntities = new ArrayList<>();

    try {

      helperCommentEntities = helperCommentRepository.findByPostSequence(postSequence);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetHelperCommentsResponseDto.success(helperCommentEntities);
  }

}