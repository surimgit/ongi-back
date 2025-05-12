package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.shoppingCart.DeleteShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetShoppingCartResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressDetailResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.PostUserAddressResponseDto;
import com.ongi.ongi_back.service.ShoppingCartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;
  
  // 장바구니 상품 등록
  @PostMapping("/product")
  public ResponseEntity<ResponseDto> postShoppingCart(
    @RequestBody @Valid PostShoppingCartRequestDto dto,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = shoppingCartService.postProduct(dto, userId);
    return response;
  }

  @PostMapping("/address")
  public ResponseEntity<? super PostUserAddressResponseDto> postUserAddress(
    @RequestBody @Valid PostUserAddressRequestDto dto,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super PostUserAddressResponseDto> response = shoppingCartService.postUserAddress(dto, userId);
    return response;
  }

  @GetMapping("/address")
  public ResponseEntity<? super GetUserAddressResponseDto> getUserAddressList(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserAddressResponseDto> response = shoppingCartService.getAddress(userId);
    return response;
  }

  @GetMapping("/address-detail")
  public ResponseEntity<? super GetUserAddressDetailResponseDto> getUserDetailAddress(
    @RequestParam("addressId") Integer id,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetUserAddressDetailResponseDto> response = shoppingCartService.getAddressDetail(id, userId);
    return response;
  }
  

  // 장바구니 리스트 출력
  @GetMapping("/product")
  public ResponseEntity<? super GetShoppingCartResponseDto> getShoppingCart(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetShoppingCartResponseDto> response = shoppingCartService.getShoppingCart(userId);
    return response;
  }

  @GetMapping("/count")
  public long getCountByUserId(
    @AuthenticationPrincipal String userId
  ){
    long response = shoppingCartService.getCountByUserId(userId);
    return response;
  }

  // 장바구니 상품 수량 변경
  @PatchMapping("/product")
  public ResponseEntity<ResponseDto> patchShoppingCart(
    @RequestBody @Valid PatchShoppingCartRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = shoppingCartService.patchProduct(requestBody, userId);
    return response;
  }

  // 장바구니 상품 삭제
  @DeleteMapping("/product")
  public ResponseEntity<ResponseDto> deleteShoppingCart(
    @RequestBody @Valid DeleteShoppingCartRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = shoppingCartService.deleteProduct(requestBody, userId);
    return response;
  }
}
