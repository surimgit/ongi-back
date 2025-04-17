package com.ongi.ongi_back.service.implement;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossConfirmResponse;
import com.ongi.ongi_back.common.entity.PaymentOrderEntity;
import com.ongi.ongi_back.common.entity.PaymentConfirmEntity;
import com.ongi.ongi_back.handler.TossErrorStatusHandler;
import com.ongi.ongi_back.repository.OrderRepository;
import com.ongi.ongi_back.repository.PaymentConfirmRepository;
import com.ongi.ongi_back.service.TossPaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TossPaymentServiceImplement implements TossPaymentService {

  private final PaymentConfirmRepository paymentRepository;
  private final OrderRepository orderRepository;
  @Override
  public ResponseEntity<ResponseDto> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception {

    
    String orderId = dto.getOrderId();
    String paymentKey = dto.getPaymentKey();
    Integer amount = dto.getAmount();

    // confirm api 요청 바디 만들기
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();
    obj.put("orderId", orderId);
    obj.put("paymentKey", paymentKey);
    obj.put("amount", amount);
    
    // confirm api 요청 인증 헤더 만들기
    String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
    Base64.Encoder encoder = Base64.getEncoder();
    byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
    String authorization = "Basic " + new String(encodedBytes);

    // /v1/payments/confirm api 호출을 위한 연결 생성
    URI uri = new URI("https://api.tosspayments.com/v1/payments/confirm");
    URL url = uri.toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorization);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);

    // /v1/payments/confirm api 호출
    OutputStream outputStream = connection.getOutputStream();
    outputStream.write(obj.toString().getBytes("UTF-8"));

    int code = connection.getResponseCode();
    boolean isSuccess = code == 200;

    // 연결이 되었다면 api 호출 응답을 받고, 연결이 되지 않았다면 에러 스트림이 할당됨
    InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

    ObjectMapper objectMapper = new ObjectMapper();
    TossConfirmResponse tossResponse = objectMapper.readValue(responseStream, TossConfirmResponse.class);
    responseStream.close();

    if (tossResponse.getFailure() != null) {
      String failureCode = tossResponse.getFailure().getCode();
      String failureMessage = tossResponse.getFailure().getMessage();
      HttpStatus status = TossErrorStatusHandler.resolve(failureCode);
      return ResponseDto.tossConfirmFailure(failureCode, failureMessage, status);
    } else {

      PostOrderRequestDto order = new PostOrderRequestDto(orderId, amount, tossResponse.getApprovedAt());

      PostConfirmRequestDto confirm = new PostConfirmRequestDto(
          paymentKey,
          orderId,
          amount,
          tossResponse.getStatus(),
          tossResponse.getApprovedAt()
      );
    
      PaymentConfirmEntity paymentConfirmEntity = new PaymentConfirmEntity(confirm);
      paymentRepository.save(paymentConfirmEntity);
    
        return ResponseDto.success(HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId) {
    
    try {

      PaymentOrderEntity orderEntity = new PaymentOrderEntity(dto, userId);
      orderRepository.save(orderEntity);            

    }catch(Exception exception) {
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

}
