package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.service.GroupPurchaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class GroupPurchaseController {
  
  private final GroupPurchaseService groupPurchaseService;

  @PostMapping({"","/"})
  public ResponseEntity<ResponseDto> postProduct(
    @RequestBody @Valid PostProductRequestDto dto,
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<ResponseDto> response = groupPurchaseService.postProduct(dto, userId);
    return response;
  }

  @PatchMapping("/{productNumber}")
  public ResponseEntity<ResponseDto> patchProduct(
    @RequestBody @Valid PatchProductRequestDto dto,
    @PathVariable("productNumber") Integer productNumber,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = groupPurchaseService.patchProduct(dto, productNumber, userId);
    return response;
  }
}
