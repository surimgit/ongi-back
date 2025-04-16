package com.ongi.ongi_back.service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;

public interface TossPaymentService {
  ResponseEntity<JSONObject> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception;
}
