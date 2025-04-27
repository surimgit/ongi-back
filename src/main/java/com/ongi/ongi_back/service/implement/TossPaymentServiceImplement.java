package com.ongi.ongi_back.service.implement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostConfirmRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderItemRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostOrderRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.GetTransactionRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.GetOrderResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossCancelResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossConfirmResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossGetPaymentKeyResponseDto;
import com.ongi.ongi_back.common.dto.response.payment.TossTransactionResponseDto;
import com.ongi.ongi_back.common.entity.PaymentOrderEntity;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.StockReservationEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.TossCancel;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.entity.PaymentCancelEntity;
import com.ongi.ongi_back.common.entity.PaymentConfirmEntity;
import com.ongi.ongi_back.common.entity.PaymentOrderEntity;
import com.ongi.ongi_back.handler.TossErrorStatusHandler;

import com.ongi.ongi_back.repository.*;
import com.ongi.ongi_back.service.TossPaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TossPaymentServiceImplement implements TossPaymentService {

  private final PaymentConformRepository paymentRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final StockReservationRepository stockReservationRepository;
  private final OrderItemRepository orderItemRepository;
  private final PaymentCancelRepository paymentCancelRepository;
  private final UserRepository userRepository;

  @Value("${toss.widget-secret-key}")
  private String widgetSecretKey;

  @Override
  public ResponseEntity<ResponseDto> confirmPayment(PostConfirmRequestDto dto, String userId) throws Exception {

      // 1. 요청 바디 만들기
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode requestBody = mapper.createObjectNode();
      requestBody.put("orderId", dto.getOrderId());
      requestBody.put("paymentKey", dto.getPaymentKey());
      requestBody.put("amount", dto.getAmount());

      // 2. Authorization 헤더 만들기
      String encodedKey = Base64.getEncoder().encodeToString((widgetSecretKey + ":").getBytes());
      String authorization = "Basic " + encodedKey;

      // 3. HttpEntity (headers + body) 구성
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Authorization", authorization);

      HttpEntity<String> httpEntity = new HttpEntity<>(requestBody.toString(), headers);

      // 4. API 호출
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<TossConfirmResponseDto> responseEntity = restTemplate.postForEntity(
              "https://api.tosspayments.com/v1/payments/confirm",
              httpEntity,
              TossConfirmResponseDto.class
      );

      TossConfirmResponseDto tossResponse = responseEntity.getBody();

      if (tossResponse == null) {
          return ResponseDto.tossFailure("NULL_RESPONSE", "토스 응답이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
      }

      // 5. 실패 처리
      if (tossResponse.getFailure() != null) {
          String failureCode = tossResponse.getFailure().getCode();
          String failureMessage = tossResponse.getFailure().getMessage();
          HttpStatus status = TossErrorStatusHandler.resolve(failureCode);
          return ResponseDto.tossFailure(failureCode, failureMessage, status);
      }

      // 6. 성공 처리
      PostConfirmRequestDto confirm = new PostConfirmRequestDto(
              dto.getPaymentKey(),
              dto.getOrderId(),
              dto.getAmount(),
              tossResponse.getStatus(),
              tossResponse.getApprovedAt()
      );

      PaymentConfirmEntity paymentConfirmEntity = new PaymentConfirmEntity(confirm);
      paymentRepository.save(paymentConfirmEntity);

      return ResponseDto.success(HttpStatus.OK);
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
    
    
    // 1. confirm api 요청 바디 만들기
    String paymentKey = dto.getPaymentKey();

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode requestBody = mapper.createObjectNode();
    requestBody.put("paymentKey", paymentKey);

    // 2. confirm api 요청 인증 헤더 만들기
    String encodedString = Base64.getEncoder().encodeToString((widgetSecretKey + ":").getBytes());
    String authorization = "Basic " + encodedString;

    // 3. HttpEntity (headers + body) 구성
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", authorization);

    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    // 4. API 호출
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<TossGetPaymentKeyResponseDto> responseEntity = restTemplate.exchange(
      "https://api.tosspayments.com/v1/payments/" + paymentKey,
      HttpMethod.GET,
      httpEntity,
      TossGetPaymentKeyResponseDto.class
    );

    // 5. 결과 받기
    TossGetPaymentKeyResponseDto tossResponse = responseEntity.getBody();

    // 5-1. 응답이 null이면 실패 처리
    if(tossResponse == null) {
      return ResponseDto.tossFailure("NULL_RESPONSE", "토스 응답이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 5-2. 응답의 status가 DONE이 아니면 실패 처리
    if(!"DONE".equals(tossResponse.getStatus())){
      return ResponseDto.tossFailure("PAYMENT_NOT_DONE", "결제가 완료되지 않았습니다.", HttpStatus.BAD_REQUEST);
    }

    // 5-3. metadata 읽기
    Map<String, String> metadata = tossResponse.getMetadata();

    if(metadata == null) return ResponseDto.noMetadata();

    // 5-4. metadata의 productSequence, productQuantities 읽기
    String productSequences = (String) metadata.get("productSequences");
    String productQuantities = (String) metadata.get("productQuantity");
    
    if (productSequences == null || productQuantities == null) {
      return ResponseDto.tossFailure("INVALID_METADATA", "상품 정보가 없습니다.", HttpStatus.BAD_REQUEST);
    }

    List<Integer> productSequencesArr = parseIntegerListFromMetadata(productSequences);
    List<Integer> productQuantitiesArr = parseIntegerListFromMetadata(productQuantities);
    
    // 5-5. 결제 성공한 결제건 상품 각각의 정보 저장하기
    for(int i = 0; i < productSequencesArr.size(); i++){
      int productSequence = productSequencesArr.get(i);
      int quantity = productQuantitiesArr.get(i);
      PostOrderItemRequestDto requestDto = new PostOrderItemRequestDto(paymentKey, productSequence, quantity);
      OrderItemEntity orderItemEntity = new OrderItemEntity(requestDto);
      orderItemRepository.save(orderItemEntity);

      StockReservationEntity stockReservationEntity = stockReservationRepository.findByUserIdAndProductSequence(userId, productSequence);
      if(stockReservationEntity != null) stockReservationRepository.deleteByUserIdAndProductSequence(userId, productSequence);
      
      ProductEntity productEntity = productRepository.findBySequence(stockReservationEntity.getProductSequence());
      if(productEntity == null) return ResponseDto.noExistProduct();

      Integer newBought = productEntity.getBoughtAmount() + quantity;
      productEntity.setBoughtAmount(newBought);

      productRepository.save(productEntity);
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
    
    // 1. 결제 승인된 제품이 없다면 해당 응답 리턴
    OrderItemEntity orderItemEntity = orderItemRepository.findByPaymentKeyAndProductSequence(paymentKey, productSequence);
    if(orderItemEntity == null) return ResponseDto.noExistShoppingCart();

    // 2. 요청 바디 만들기
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode requestBody = mapper.createObjectNode();
    requestBody.put("paymentKey", paymentKey);
    requestBody.put("cancelReason", cancelReason);

    // 3. Authorization 헤더 만들기
    String encodedString = Base64.getEncoder().encodeToString((widgetSecretKey + ":").getBytes());
    String authorization = "Basic " + encodedString;

    // 3. HttpEntity (headers + body) 구성
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", authorization);

    HttpEntity<String> httpEntity = new HttpEntity<>(requestBody.toString(), headers);

    // 4. API 호출 
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<TossCancelResponseDto> responseEntity = restTemplate.postForEntity(
      "https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel",
      httpEntity,
      TossCancelResponseDto.class  
    );

    TossCancelResponseDto tossResponse = responseEntity.getBody();

    if (tossResponse == null) {
      return ResponseDto.tossFailure("NULL_RESPONSE", "토스 응답이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    
    }
    
    // 6. 취소 내역 저장
    List<TossCancel> cancels = tossResponse.getCancels();
  
    if(cancels == null) return ResponseDto.notCancelablePayment();

    try{
      for(TossCancel cancel: cancels){
        PaymentCancelEntity paymentCancelEntity = new PaymentCancelEntity(cancel, paymentKey, cancelReason, productSequence);
        paymentCancelRepository.save(paymentCancelEntity);
        orderItemRepository.deleteByPaymentKeyAndProductSequence(paymentKey, productSequence);
      }
    } catch(Exception exception) {
      return ResponseDto.databaseError();
    }
    

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public List<TossTransactionResponseDto> getTransaction(GetTransactionRequestDto dto, String userId) throws Exception {
    UserEntity user = userRepository.findByUserId(userId);
    // if(!user.getIsAdmin()) return null;

    String startDate = dto.getStartDate();
    String endDate = dto.getEndDate();

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();
    obj.put("startDate", startDate);
    obj.put("endDate", endDate);

    String widgetSecurityHeader = widgetSecretKey;
    String encodedString = Base64.getEncoder().encodeToString((widgetSecurityHeader + ":").getBytes());
    String authorization = "Basic " + encodedString;

    URI uri = new URI("https://api.tosspayments.com/v1/transactions?startDate=" + startDate + "&endDate=" + endDate);
    URL url = uri.toURL();

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorization);
    connection.setRequestMethod("GET");

    int code = connection.getResponseCode();
    boolean isSuccess = code == 200;

    InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

    if(!isSuccess) {
      ResponseDto response = mapper.readValue(responseStream, ResponseDto.class);
      String errorCode = response.getCode();
      String errorMessage = response.getMessage();
      System.out.println(ResponseDto.tossFailure(errorCode, errorMessage, HttpStatus.valueOf(errorCode))); 
    }

    String responseJson;
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream))){
      responseJson = reader
        .lines()
        .collect(Collectors.joining("\n"));
    }

    List<TossTransactionResponseDto> list = mapper.readValue(responseJson, new TypeReference<List<TossTransactionResponseDto>>() {});

    return list;
  }



}