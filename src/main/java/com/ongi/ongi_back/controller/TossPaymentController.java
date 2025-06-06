package com.ongi.ongi_back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.payment.GetTransactionRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderItemRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.GetOrderResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossTransactionResponseDto;
import com.ongi.ongi_back.service.TossPaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class TossPaymentController {

  private final TossPaymentService tossPaymentService;
  
  @PostMapping("/confirm")
  public ResponseEntity<ResponseDto> confirmPayment(
    @RequestBody @Valid PostConfirmRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) throws Exception {
    ResponseEntity<ResponseDto> response = tossPaymentService.confirmPayment(requestBody, userId);
    return response;
  }

  @PostMapping("/")
  public ResponseEntity<ResponseDto> postOrder(
    @RequestBody @Valid PostOrderRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<ResponseDto> response = tossPaymentService.postOrder(requestBody, userId);
    return response;
  }

  @GetMapping("/")
  public ResponseEntity<? super GetOrderResponseDto> getOrder(
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<? super GetOrderResponseDto> response = tossPaymentService.getRecentlyOrder(userId);
    return response;
  }

  @PostMapping("/order-items")
  public ResponseEntity<ResponseDto> postOrderItems(
    @RequestBody @Valid PostOrderItemRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) throws Exception {
    ResponseEntity<ResponseDto> response = tossPaymentService.postOrderItem(requestBody, userId);
    return response;
  }

  @PostMapping("/cancel")
  public ResponseEntity<ResponseDto> postCancel(
    @RequestBody @Valid PostCancelRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ) throws Exception {
    ResponseEntity<ResponseDto> response = tossPaymentService.postCancelPayment(requestBody, userId);
    return response;
  }

  @GetMapping("/transaction")
  public List<TossTransactionResponseDto> getTransaction(
    @RequestBody @Valid GetTransactionRequestDto requestBody,
    @AuthenticationPrincipal String userId
  )throws Exception{
    List<TossTransactionResponseDto> response = tossPaymentService.getTransaction(requestBody, userId);
    return response;
  }
}
