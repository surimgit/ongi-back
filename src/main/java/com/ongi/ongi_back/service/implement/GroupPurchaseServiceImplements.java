package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.group.PatchProductRequestDto;
import com.ongi.ongi_back.common.dto.request.group.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
import com.ongi.ongi_back.repository.GroupPurchaseRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.GroupPurchaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupPurchaseServiceImplements implements GroupPurchaseService{

  private final GroupPurchaseRepository groupPurchaseRepository;
  private final UserRepository userRepository;
  @Override
  public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, String userId) {
    
    try {
      ProductEntity productEntity = new ProductEntity(dto, userId);
      groupPurchaseRepository.save(productEntity);
    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ResponseDto> patchProduct(PatchProductRequestDto dto, Integer productNumber, String userId) {

    try {
      
      ProductEntity productEntity = groupPurchaseRepository.findByUserIdAndSequence(userId, productNumber);
      productEntity.patch(dto);

      groupPurchaseRepository.save(productEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);

  }
  
}
