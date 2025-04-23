package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.group.PatchProductQuantityRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostStockReservationRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetReservationResponseDto;

public interface GroupPurchaseService {
  ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer sequence, String userId);
  ResponseEntity<ResponseDto> patchProduct(PatchProductQuantityRequestDto dto, Integer sequence, String userId);
  ResponseEntity<? super GetProductListResponseDto> getProductList(String userId, String category, String name);
  ResponseEntity<? super GetDetailProductDto> getDetailProduct(String userId, Integer sequence);
  ResponseEntity<ResponseDto> postStockReservation(PostStockReservationRequestDto dto, String userId);
  ResponseEntity<? super GetReservationResponseDto> getStockReservation(Integer sequence);
}
