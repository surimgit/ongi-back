package com.ongi.ongi_back.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/payments")
public class TossPaymentController {
  
  @PostMapping("/confirm")
  public ResponseEntity<JSONObject> confirmPayment(
    @RequestBody @Valid PostConfirmRequestDto requestBody,
    @AuthenticationPrincipal String userId
  ){
    ResponseEntity<JSONObject> 
  }
}
