package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;

@Service
public interface GroupPurchaseService {
  ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer sequence, String userId);
  ResponseEntity<? super GetProductListResponseDto> getProductList(String userId, String category, String name);
  ResponseEntity<? super GetDetailProductDto> getDetailProduct(String userId, Integer sequence);
}
