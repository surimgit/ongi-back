package com.ongi.ongi_back.service.implement;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderItemRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.GetOrderResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossCancelResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossConfirmResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossGetPaymentKeyResponseDto;
import com.ongi.ongi_back.common.entity.PaymentOrderEntity;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.vo.TossCancel;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.entity.PaymentCancelEntity;
import com.ongi.ongi_back.common.entity.PaymentConfirmEntity;
import com.ongi.ongi_back.handler.TossErrorStatusHandler;
import com.ongi.ongi_back.repository.*;
import com.ongi.ongi_back.service.TossPaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TossPaymentServiceImplement implements TossPaymentService {

  private final PaymentConfirmRepository paymentRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderItemRepository orderItemRepository;
  private final PaymentCancelRepository paymentCancelRepository;

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
    String encodedString = Base64.getEncoder().encodeToString((widgetSecretKey + ":").getBytes());
    String authorization = "Basic " + encodedString;

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

    TossConfirmResponseDto tossResponse = objectMapper.readValue(responseStream, TossConfirmResponseDto.class);

    responseStream.close();

    if (tossResponse.getFailure() != null) {
      String failureCode = tossResponse.getFailure().getCode();
      String failureMessage = tossResponse.getFailure().getMessage();
      HttpStatus status = TossErrorStatusHandler.resolve(failureCode);
      return ResponseDto.tossConfirmFailure(failureCode, failureMessage, status);
    } else {

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

  @Override
  public ResponseEntity<? super GetOrderResponseDto> getRecentlyOrder(String userId) {
    PaymentOrderEntity paymentOrderEntity;

    try {

      paymentOrderEntity = orderRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
      if(paymentOrderEntity == null) return ResponseDto.productNotFound();
      
    } catch(Exception exception) {
      return ResponseDto.databaseError();
    }

    return GetOrderResponseDto.success(paymentOrderEntity);
  }

  @Override
  public ResponseEntity<ResponseDto> postOrderItem(PostOrderItemRequestDto dto, String userId) throws Exception {
    
    String paymentKey = dto.getPaymentKey();

    System.out.println(paymentKey);
    // confirm api 요청 바디 만들기
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();
    obj.put("paymentKey", paymentKey);

    // confirm api 요청 인증 헤더 만들기
    String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
    String encodedString = Base64.getEncoder().encodeToString((widgetSecretKey + ":").getBytes());
    String authorization = "Basic " + encodedString;

    // /v1/payments/confirm api 호출을 위한 연결 생성
    URI uri = new URI("https://api.tosspayments.com/v1/payments/" + paymentKey);
    URL url = uri.toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorization);
    connection.setRequestMethod("GET");

    int code = connection.getResponseCode();
    boolean isSuccess = code == 200;

    // 연결이 되었다면 api 호출 응답을 받고, 연결이 되지 않았다면 에러 스트림이 할당됨
    InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

    String responseJson = new BufferedReader(new InputStreamReader(responseStream))
    .lines()
    .collect(Collectors.joining("\n"));

    TossGetPaymentKeyResponseDto tossResponse = mapper.readValue(responseJson, TossGetPaymentKeyResponseDto.class);
    
    String tossResponseStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tossResponse);

    Map<String, String> metadata = tossResponse.getMetadata();

    if(metadata == null) return ResponseDto.noMetadata();

    // productSequence 처리
    String productSequences = (String) metadata.get("productSequences");
    List<Integer> productSequencesArr = parseIntegerListFromMetadata(productSequences);

    // productQuantity 처리
    String productQuantities = (String) metadata.get("productQuantity");
    List<Integer> productQuantitiesArr = parseIntegerListFromMetadata(productQuantities);

    // productSequencesArr와 productQuantitiesArr의 크기가 동일하다는 가정 하에 처리
    List<ProductEntity> productEntities = productRepository.findBySequences(productSequencesArr);

    
    for(int i = 0; i < productEntities.size(); i++){
      ProductEntity productEntity = productEntities.get(i);
      int quantity = productQuantitiesArr.get(i);
      OrderItemEntity orderItemEntity = new OrderItemEntity(productEntity, quantity, paymentKey);
      orderItemRepository.save(orderItemEntity);
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  private List<Integer> parseIntegerListFromMetadata(String metadataValue) {
    String[] arrStr = metadataValue.split(",");
    List<Integer> result = new ArrayList<>();
    for (String str : arrStr) {
        result.add(Integer.parseInt(str.trim()));
    }
    return result;
  }

  @Override
  public ResponseEntity<ResponseDto> postCancelPayment(PostCancelRequestDto dto, String userId) throws Exception {
    
    String paymentKey = dto.getPaymentKey();
    String cancelReason = dto.getCancelReason();
    Integer cancelAmount = dto.getCancelAmount();
    Integer productSequence = dto.getProductSequence();

    OrderItemEntity orderItemEntity = orderItemRepository.findByPaymentKeyAndProductSequence(paymentKey, productSequence);
    if(orderItemEntity == null) return ResponseDto.noExistShoppingCart();

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();
    obj.put("paymentKey", paymentKey);
    obj.put("cancelReason", cancelReason);

    String widgetSecurityHeader = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
    String encodedString = Base64.getEncoder().encodeToString((widgetSecurityHeader + ":").getBytes());
    String authorization = "Basic " + encodedString;

    URI uri = new URI("https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel");
    URL url = uri.toURL();

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorization);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);

    String jsonBody = "{"
    + "\"cancelReason\": \"" + cancelReason + "\","
    + "\"cancelAmount\": " + cancelAmount
    + "}";

    try(OutputStream outputStream = connection.getOutputStream()){
      byte[] input = jsonBody.getBytes("UTF-8");
      outputStream.write(input, 0, input.length);
    }

    int code = connection.getResponseCode();
    boolean isSuccess = code == 200;

    InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
    TossCancelResponseDto tossResponse = mapper.readValue(responseStream, TossCancelResponseDto.class);

    String tossResponseStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tossResponse);
    System.out.println("📦 Toss 응답 객체 (DTO):");
    System.out.println(tossResponseStr);

    List<TossCancel> cancels = tossResponse.getCancels();
  
    if(cancels == null) return ResponseDto.noExistShoppingCart();

    for(TossCancel cancel: cancels){
      PaymentCancelEntity paymentCancelEntity = new PaymentCancelEntity(cancel, paymentKey, cancelReason, productSequence);
      paymentCancelRepository.save(paymentCancelEntity);
      orderItemRepository.deleteByPaymentKeyAndProductSequence(paymentKey, productSequence);
    }

    return ResponseDto.success(HttpStatus.OK);
  }



}