package com.ongi.ongi_back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishListResponseDto;
import com.ongi.ongi_back.common.dto.response.wishList.GetWishResponseDto;
import com.ongi.ongi_back.common.entity.WishListEntity;
import com.ongi.ongi_back.common.vo.WishVO;
import com.ongi.ongi_back.repository.WishListRepository;
import com.ongi.ongi_back.service.WishListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListServiceImplement implements WishListService {

  private final WishListRepository wishListRepository;

  @Override
  public ResponseEntity<ResponseDto> postWishList(String userId, Integer productSequence) {

    try{

      WishListEntity wishListEntity = wishListRepository.findByUserIdAndProductSequence(userId, productSequence);
      if(wishListEntity != null) return ResponseDto.alreadyLikedPost();

      wishListEntity = new WishListEntity(userId, productSequence);

      wishListRepository.save(wishListEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<? super GetWishListResponseDto> getWishList(String userId) {
    
    List<WishVO> wishListEntities;

    try{
      
      wishListEntities = wishListRepository.findByUserId(userId);
      if(wishListEntities == null) return ResponseDto.noExistWishList();

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetWishListResponseDto.success(wishListEntities);
  }

  @Override
  public ResponseEntity<? super GetWishResponseDto> getWishByUserAndSequence(String userId, Integer productSequence) {
    
    WishListEntity wishListEntity;

    try{

      wishListEntity = wishListRepository.findByUserIdAndProductSequence(userId, productSequence);
      if(wishListEntity == null) return ResponseDto.noExistWishList();

    } catch(Exception exception) {
      return ResponseDto.databaseError();
    }

    return GetWishResponseDto.success(wishListEntity);
  }

  @Override
  public ResponseEntity<ResponseDto> deleteWishList(String userId, Integer productSequence) {
    
    try{

      WishListEntity wishListEntity = wishListRepository.findByUserIdAndProductSequence(userId, productSequence);
      if(wishListEntity == null) return ResponseDto.noExistWishList();

      String wishUser = wishListEntity.getUserId();
      boolean isMatch = userId.equals(wishUser);

      if(!isMatch) return ResponseDto.noPermission();

      wishListRepository.delete(wishListEntity);

    } catch(Exception exception) {
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }


  
}
