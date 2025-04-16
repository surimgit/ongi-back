package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.group.GetDetailProductDto;
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

  
}
