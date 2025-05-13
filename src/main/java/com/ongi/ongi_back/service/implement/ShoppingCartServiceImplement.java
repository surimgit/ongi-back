package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.shoppingCart.DeleteShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetShoppingCartResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressDetailResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.PostUserAddressResponseDto;
import com.ongi.ongi_back.common.entity.ShoppingCartEntity;
import com.ongi.ongi_back.common.entity.UserAddressEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.ShoppingCartVO;
import com.ongi.ongi_back.common.vo.UserAddressVO;
import com.ongi.ongi_back.repository.ShoppingCartRepository;
import com.ongi.ongi_back.repository.UserAddressRepository;
import com.ongi.ongi_back.service.ShoppingCartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartServiceImplement implements ShoppingCartService {

  private final ShoppingCartRepository shoppingCartRepository;
  private final UserAddressRepository userAddressRepository;

  @Override
  public ResponseEntity<ResponseDto> postProduct(PostShoppingCartRequestDto dto, String userId) {

    ShoppingCartEntity cartEntity;
    try{

      Integer productSequence = dto.getProductSequence();
      cartEntity = shoppingCartRepository.findByUserIdAndProductSequence(userId, productSequence);
      
      if(cartEntity != null) {
        Integer quantity = cartEntity.getQuantity() + dto.getQuantity();
        cartEntity.patch(quantity);
      } else {
        cartEntity = new ShoppingCartEntity(dto, userId);
      }

      shoppingCartRepository.save(cartEntity);

    } catch(Exception exception) {
      log.error("에러 발생", exception);
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchShoppingCartRequestDto dto, String userId) {

    try {

      Integer shoppingCartSequence = dto.getShoppingCartSequence();
      
      ShoppingCartEntity cartEntity = shoppingCartRepository.findByUserIdAndShoppingCartSequence(userId, shoppingCartSequence);
      if(cartEntity == null) return ResponseDto.noExistProduct();

      String cartUserId = cartEntity.getUserId();
      boolean isUser = cartUserId.equals(userId);

      if(!isUser) return ResponseDto.noPermission();

      cartEntity.patch(dto);
      shoppingCartRepository.save(cartEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetShoppingCartResponseDto> getShoppingCart(String userId) {
    List<ShoppingCartVO> list = new ArrayList<>();

    try {

      list = shoppingCartRepository.findCartDetails(userId);

    } catch(Exception exception){
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetShoppingCartResponseDto.success(list);
  }

  @Override
  public ResponseEntity<ResponseDto> deleteProduct(DeleteShoppingCartRequestDto dto, String userId) {

    try{
      
      Integer shoppingCartSequence = dto.getShoppingCartSequence();
      
      ShoppingCartEntity cartEntity = shoppingCartRepository.findByShoppingCartSequenceAndUserId(shoppingCartSequence, userId);

      String cartUserId = cartEntity.getUserId();
      boolean isUser = cartUserId.equals(userId);

      if(!isUser) return ResponseDto.noPermission();

      shoppingCartRepository.delete(cartEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public long getCountByUserId(String userId) {
    return shoppingCartRepository.countByUserId(userId);
  }

  @Override
  public ResponseEntity<? super PostUserAddressResponseDto> postUserAddress(PostUserAddressRequestDto dto, String userId) {
    
    Integer addressId;
    String addressType = dto.getAddressType();
    
    try{
      if(addressType.equals("신규")){

        UserAddressEntity userAddressEntity = new UserAddressEntity(dto, userId);
        String addressLabel = dto.getAddressLabel();

        UserAddressEntity existUserAddress = userAddressRepository.findByUserIdAndAddressLabel(userId, addressLabel);
      
        if(existUserAddress != null) return ResponseDto.existUserAddress();

        userAddressRepository.save(userAddressEntity);
        addressId = userAddressEntity.getId();
      }else{
        String addressLabel = dto.getAddressLabel();
        UserAddressEntity existUserAddress = userAddressRepository.findByUserIdAndAddressLabel(userId, addressLabel);

        if(existUserAddress == null) return ResponseDto.noExistUserAddress();

        addressId = existUserAddress.getId();

      }

      
      
    } catch(Exception exception){
      log.error("데이터베이스 에러", exception.getMessage(), exception);
      return ResponseDto.databaseError();
    }

    return PostUserAddressResponseDto.success(addressId);
  }

  @Override
  public ResponseEntity<? super GetUserAddressResponseDto> getAddress(String userId) {
    
    List<UserAddressVO> addressLabelList;
    
    try {
      
      addressLabelList = userAddressRepository.findAddressLabelByUserId(userId);
      if(addressLabelList == null) return ResponseDto.noExistUserAddress();

    }catch(Exception exception){
      log.error("데이터베이스 에러", exception.getMessage(), exception);
      return ResponseDto.databaseError();
    }

    return GetUserAddressResponseDto.success(addressLabelList);
  }

  @Override
  public ResponseEntity<? super GetUserAddressDetailResponseDto> getAddressDetail(Integer id, String userId) {

    UserAddressEntity userAddressEntity;

    try {

      userAddressEntity = userAddressRepository.findByUserIdAndId(userId, id);

    }catch(Exception exception){
      log.error("데이터베이스 에러", exception.getMessage(), exception);
      return ResponseDto.databaseError();
    }

    return GetUserAddressDetailResponseDto.success(userAddressEntity);
  }
}
