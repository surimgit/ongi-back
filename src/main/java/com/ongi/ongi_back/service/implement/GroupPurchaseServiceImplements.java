package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ongi.ongi_back.common.dto.request.group.PatchProductQuantityRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostStockReservationRequestDto;
import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductReviewResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetReservationResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetReviewImagesResponseDto;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.ProductReviewEntity;
import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.entity.StockReservationEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.ReviewImagesVO;
import com.ongi.ongi_back.common.vo.StockReservationVO;
import com.ongi.ongi_back.repository.*;
import com.ongi.ongi_back.service.GroupPurchaseService;
import com.ongi.ongi_back.service.TossPaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupPurchaseServiceImplements implements GroupPurchaseService{

  private final ReviewImagesRepository reviewImagesRepository;
  private final ProductReviewRepository productReviewRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final StockReservationRepository stockReservationRepository;
  private final OrderItemRepository orderItemRepository;

  private final TossPaymentService tossPaymentService;
  
  @Override
  public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId) {
    
    try {
      ProductEntity productEntity = new ProductEntity(dto, userId);
      productRepository.save(productEntity);
    } catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage(), exception);
      return ResponseDto.databaseError();
    }

    log.debug(userId + "가 상품을 등록했습니다.");
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer sequence, String userId) {

    try {
      
      ProductEntity productEntity = productRepository.findByUserIdAndSequence(userId, sequence);
      productEntity.patch(dto);

      productRepository.save(productEntity);

    } catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    log.debug(sequence + "번 상품의 정보가 변경되었습니다.");
    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getProductList(String userId, String category, String name) {
    
    List<ProductEntity> productEntities = new ArrayList<>();
    String filterType;

    try {
      
      boolean hasCategory = category != null && !category.isEmpty() && !category.equals("전체");
      boolean hasName = name != null && !name.isEmpty();
  
      // 카테고리와 이름이 모두 있는 경우
      if (hasCategory && hasName) {
        productEntities = productRepository.findByCategoryAndNameContainingOrderBySequenceDesc(category, name);
        filterType = "categoryAndName";
      }
      // 카테고리만 있는 경우
      else if (hasCategory) {
        productEntities = productRepository.findByCategoryOrderBySequenceDesc(category);
        filterType = "category";
      }
      // 이름만 있는 경우
      else if (hasName) {
        productEntities = productRepository.findByNameContainingOrderBySequenceDesc(name);
        filterType = "name";
      }
      // 아무 필터도 없는 경우 (전체 조회)
      else {
        productEntities = productRepository.findByOrderBySequenceDesc();
        filterType = "all";
      }

    } catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    log.info(category + " 카테고리의 공동구매 상품 리스트를 불러옵니다.");
    return GetProductListResponseDto.success(productEntities, filterType);
  }

  @Override
  public ResponseEntity<? super GetDetailProductDto> getDetailProduct(String userId, Integer sequence) {

    ProductEntity productEntity;

    try {
      
      productEntity = productRepository.findBySequence(sequence);
      if(productEntity == null) return ResponseDto.noExistProduct();

    } catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    return GetDetailProductDto.success(productEntity);  
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchProductQuantityRequestDto dto, Integer sequence, String userId) {

    try{
      int quantity = dto.getQuantity();

      ProductEntity productEntity = productRepository.findBySequence(sequence);
      
      int nowBoughtAmount = productEntity.getBoughtAmount();
      int productQuantities = productEntity.getProductQuantity();
      int completeQuantity = nowBoughtAmount + quantity;

      if(completeQuantity > productQuantities) return ResponseDto.outOfStock();

      productEntity.setBoughtAmount(completeQuantity);
      productRepository.save(productEntity);

    } catch (Exception exception){
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    log.debug(sequence + "번 상품의 정보를 변경했습니다.");
    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> postStockReservation(PostStockReservationRequestDto dto, String userId) {

    List<StockReservationVO> reservationList = dto.getList();
    if(reservationList == null) return ResponseDto.noExistProduct();

    for (StockReservationVO vo : reservationList) {
        Integer sequence = vo.getProductSequence();
        Integer quantity = vo.getQuantity();

        ProductEntity productEntity = productRepository.findBySequence(sequence);
        if (productEntity == null) {
            return ResponseDto.noExistProduct(); 
        }

        int reservedQuantity = stockReservationRepository.sumQuantityByProductSequence(sequence);
        int nowBought = productEntity.getBoughtAmount();
        int available = productEntity.getProductQuantity() - nowBought - reservedQuantity;

        if (quantity > available) {
            return ResponseDto.outOfStock(); 
        }

        StockReservationEntity entity = new StockReservationEntity(vo, userId);
        stockReservationRepository.save(entity);
    }

    return ResponseDto.success(HttpStatus.OK);
}

  @Override
  public ResponseEntity<? super GetReservationResponseDto> getStockReservation(Integer sequence) {


    int quantity;

    try{

      quantity = stockReservationRepository.sumQuantityByProductSequence(sequence);

    }catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    return GetReservationResponseDto.success(quantity);
  }

  @Override
  public ResponseEntity<? super GetProductReviewResponseDto> getProductReview(Integer sequence) {

    List<ProductReviewEntity> productReviewEntities;

    try {
      
      productReviewEntities = productReviewRepository.findByProductSequence(sequence);

    }catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    return GetProductReviewResponseDto.success(productReviewEntities);
  }

  @Override
  public ResponseEntity<? super GetReviewImagesResponseDto> getReviewImages(Integer sequence) {

    List<ReviewImagesVO> reviewImagesEntities;
    
    try {
      
      reviewImagesEntities = reviewImagesRepository.findReviewImages(sequence);

    }catch(Exception exception) {
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    return GetReviewImagesResponseDto.success(reviewImagesEntities);
  }

  @Override
  public ResponseEntity<ResponseDto> deleteProduct(Integer sequence, String userId) {
    
    try {
      ProductEntity productEntity = productRepository.findBySequence(sequence);
      if(productEntity == null) return ResponseDto.noExistProduct();

      String writerId = productEntity.getUserId();
      boolean isWrite = writerId.equals(userId);

      if(!isWrite) return ResponseDto.noPermission();

      List<PostCancelRequestDto> list = orderItemRepository.findByCancelInfo(sequence);

      for(PostCancelRequestDto dto: list){
        tossPaymentService.postCancelPayment(dto, userId);
      }
      
      productRepository.delete(productEntity);

    } catch(Exception exception){
      log.error("데이터베이스 에러", exception.getMessage());
      return ResponseDto.databaseError();
    }

    log.debug(sequence + "번 상품이 삭제되었습니다.");

    return ResponseDto.success(HttpStatus.OK);
  }
  
}
