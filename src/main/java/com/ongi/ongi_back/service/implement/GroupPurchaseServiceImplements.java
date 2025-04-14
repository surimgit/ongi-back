package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetProductListResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.ProductRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.GroupPurchaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupPurchaseServiceImplements implements GroupPurchaseService{

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
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
  public ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer productNumber, String userId) {

    try {
      
      ProductEntity productEntity = productRepository.findByUserIdAndSequence(userId, productNumber);
      productEntity.patch(dto);

      productRepository.save(productEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<? super GetProductListResponseDto> getProductList(String userId) {
    
    List<ProductEntity> productEntities = new ArrayList<>();

    try {

      productEntities = productRepository.findByOrderBySequenceDesc();

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetProductListResponseDto.success(productEntities);
  }
  
}
