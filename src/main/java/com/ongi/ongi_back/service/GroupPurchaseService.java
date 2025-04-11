package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface GroupPurchaseService {
  ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId);
  ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer productNumber, String userId);
}
