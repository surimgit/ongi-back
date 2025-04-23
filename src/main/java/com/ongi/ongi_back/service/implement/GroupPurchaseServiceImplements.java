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
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetReservationResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.StockReservationEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.common.vo.StockReservationVO;
import com.ongi.ongi_back.repository.ProductRepository;
import com.ongi.ongi_back.repository.StockReservationRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.GroupPurchaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupPurchaseServiceImplements implements GroupPurchaseService{

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final StockReservationRepository stockReservationRepository;
  
  @Override
  public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId) {
    
    try {
      ProductEntity productEntity = new ProductEntity(dto, userId);
      productRepository.save(productEntity);
    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer sequence, String userId) {

    try {
      
      ProductEntity productEntity = productRepository.findByUserIdAndSequence(userId, sequence);
      productEntity.patch(dto);

      productRepository.save(productEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

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
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetProductListResponseDto.success(productEntities, filterType);
  }

  @Override
  public ResponseEntity<? super GetDetailProductDto> getDetailProduct(String userId, Integer sequence) {

    ProductEntity productEntity;
    try {
      
      productEntity = productRepository.findBySequence(sequence);
      if(productEntity == null) return ResponseDto.noExistProduct();

    } catch(Exception exception) {
      exception.printStackTrace();
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
      return ResponseDto.databaseError();
    }

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
      return ResponseDto.databaseError();
    }

    return GetReservationResponseDto.success(quantity);
  }


  
}
