package com.ongi.ongi_back.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderItemRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.GetTransactionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.GetOrderResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossTransactionResponseDto;

public interface TossPaymentService {
  ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId);
  ResponseEntity<? super GetOrderResponseDto> getRecentlyOrder(String userId);
  ResponseEntity<ResponseDto> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception;
  ResponseEntity<ResponseDto> postOrderItem(PostOrderItemRequestDto dto, String userId) throws Exception;
  ResponseEntity<ResponseDto> postCancelPayment(PostCancelRequestDto dto, String userId) throws Exception;
  List<TossTransactionResponseDto> getTransaction(GetTransactionRequestDto dto, String userId) throws Exception;
}