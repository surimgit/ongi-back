package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.shoppingCart.DeleteShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PatchShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.request.shoppingCart.PostShoppingCartRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.shoppingCart.GetShoppingCartResponseDto;

@Service
public interface ShoppingCartService {
    ResponseEntity<ResponseDto> postProduct(PostShoppingCartRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchProduct(PatchShoppingCartRequestDto dto, String userId);
    ResponseEntity<? super GetShoppingCartResponseDto> getShoppingCart(String userId);
    ResponseEntity<ResponseDto> deleteProduct(DeleteShoppingCartRequestDto dto, String userId);
}
