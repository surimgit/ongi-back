package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;

public interface TossPaymentService {
  ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId);
  ResponseEntity<ResponseDto> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception;
}