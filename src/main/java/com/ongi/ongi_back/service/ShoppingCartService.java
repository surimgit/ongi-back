package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.shoppingCart.DeleteShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetShoppingCartResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressDetailResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetUserAddressResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.PostUserAddressResponseDto;

public interface ShoppingCartService {
    ResponseEntity<ResponseDto> postProduct(PostShoppingCartRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchProduct(PatchShoppingCartRequestDto dto, String userId);
    ResponseEntity<? super GetShoppingCartResponseDto> getShoppingCart(String userId);
    ResponseEntity<ResponseDto> deleteProduct(DeleteShoppingCartRequestDto dto, String userId);
    ResponseEntity<? super PostUserAddressResponseDto> postUserAddress(PostUserAddressRequestDto dto, String userId);

    ResponseEntity<? super GetUserAddressResponseDto> getAddress(String userId);
    ResponseEntity<? super GetUserAddressDetailResponseDto> getAddressDetail(Integer id, String userId);
    
    
    long getCountByUserId(String userId);
}
