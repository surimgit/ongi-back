package com.ongi.ongi_back.service.implement;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.service.TossPaymentService;

@Service
public class TossPaymentServiceImplement implements TossPaymentService {

  @Override
  public ResponseEntity<JSONObject> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception {

    String orderId = dto.getOrderId();
    String paymentKey = dto.getPaymentKey();
    Integer amount = dto.getAmount();

    // confirm api 요청 바디 만들기
    JSONObject obj = new JSONObject();
    obj.put("orderId", orderId);
    obj.put("paymentKey", paymentKey);
    obj.put("amount", amount);

    // confirm api 요청 인증 헤더 만들기
    String widgetSecretKey = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm";
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

    Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
    JSONObject jsonObject = (JSONObject) new JSONParser().parse(reader);
    responseStream.close();

    

    return ResponseEntity.status(code).body(jsonObject);
  }
}
