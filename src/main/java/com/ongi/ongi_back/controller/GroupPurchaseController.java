package com.ongi.ongi_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ongi.ongi_back.common.dto.request.group.PatchProductQuantityRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostStockReservationRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductReviewResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetReservationResponseDto;
import com.ongi.ongi_back.service.GroupPurchaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class GroupPurchaseController {
  
  private final GroupPurchaseService groupPurchaseService;

  @PostMapping("/write")
  public ResponseEntity<ResponseDto> postProduct(
    @RequestBody @Valid PostProductRequestDto dto,
    @AuthenticationPrincipal String userId
  ) {
    ResponseEntity<ResponseDto> response = groupPurchaseService.postProduct(dto, userId);
    return response;
  }

  @PatchMapping("/{sequence}")
  public ResponseEntity<ResponseDto> patchProduct(
    @RequestBody @Valid PatchProductRequestDto dto,
    @PathVariable("sequence") Integer sequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = groupPurchaseService.patchProduct(dto, sequence, userId);
    return response;
  }

  @PatchMapping("/{sequence}/quantity")
  public ResponseEntity<ResponseDto> patchProductQuantity(
    @RequestBody @Valid PatchProductQuantityRequestDto dto,
    @PathVariable("sequence") Integer sequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = groupPurchaseService.patchProduct(dto, sequence, userId);
    return response;
  }

  @GetMapping({"/",""})
  public ResponseEntity<? super GetProductListResponseDto> getProductList(
    @RequestParam(value = "category", required = false) String category,
    @RequestParam(value = "name", required = false) String name,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetProductListResponseDto> response = groupPurchaseService.getProductList(userId, category, name);
    return response;
  }

  @GetMapping("/{sequence}")
  public ResponseEntity<? super GetDetailProductDto> getDetailProduct(
    @PathVariable("sequence") Integer sequence,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetDetailProductDto> response = groupPurchaseService.getDetailProduct(userId, sequence);
    return response;
  }

  @PostMapping("/reserve")
  public ResponseEntity<ResponseDto> postReserve(
    @RequestBody @Valid PostStockReservationRequestDto dto,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = groupPurchaseService.postStockReservation(dto, userId);
    return response;
  }

  @GetMapping("/{sequence}/reserve")
  public ResponseEntity<? super GetReservationResponseDto> getReserve(
    @PathVariable("sequence") Integer sequence
  ){
    ResponseEntity<? super GetReservationResponseDto> response = groupPurchaseService.getStockReservation(sequence);
    return response;
  }

  @GetMapping("/{sequence}/review")
  public ResponseEntity<? super GetProductReviewResponseDto> getProductReview(
    @PathVariable("sequence") Integer sequence
  ){
    ResponseEntity<? super GetProductReviewResponseDto> response = groupPurchaseService.getProductReview(sequence);
    return response;
  }
}

