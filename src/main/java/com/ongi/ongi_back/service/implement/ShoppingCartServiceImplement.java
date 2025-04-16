package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.shoppingCart.DeleteShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetShoppingCartResponseDto;
import com.ongi.ongi_back.common.entity.CartEntity;
import com.ongi.ongi_back.common.vo.ShoppingCartVO;
import com.ongi.ongi_back.repository.ShoppingCartRepository;
import com.ongi.ongi_back.service.ShoppingCartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImplement implements ShoppingCartService {
  private final ShoppingCartRepository shoppingCartRepository;

  @Override
  public ResponseEntity<ResponseDto> postProduct(PostShoppingCartRequestDto dto, String userId) {

    CartEntity cartEntity;
    try{

      Integer productSequence = dto.getProductSequence();
      cartEntity = shoppingCartRepository.findByUserIdAndProductSequence(userId, productSequence);
      
      if(cartEntity != null) {
        Integer quantity = cartEntity.getQuantity() + dto.getQuantity();
        cartEntity.patch(quantity);
      } else {
        cartEntity = new CartEntity(dto, userId);
      }

      shoppingCartRepository.save(cartEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchShoppingCartRequestDto dto, String userId) {

    try {

      Integer shoppingCartSequence = dto.getShoppingCartSequence();
      
      CartEntity cartEntity = shoppingCartRepository.findByUserIdAndShoppingCartSequence(userId, shoppingCartSequence);
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
    List<CartEntity> list = new ArrayList<>();

    try {

      list = shoppingCartRepository.findByUserId(userId);
      if(list.size() == 0) return ResponseDto.noExistShoppingCart();

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
      
      CartEntity cartEntity = shoppingCartRepository.findByShoppingCartSequenceAndUserId(shoppingCartSequence, userId);
      if(cartEntity == null) return ResponseDto.noExistShoppingCart();

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
}
