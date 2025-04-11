package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.Request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.Request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.Response.ResponseDto;

public interface GroupPurchaseService {
  ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto);
  ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto);
}
