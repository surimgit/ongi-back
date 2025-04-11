package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.Request.PostProductRequestDto;
import com.ongi.ongi_back.common.dto.Response.ResponseDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
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
  public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto) {
    

    try {
      String userId = "asdf1234";
      ProductEntity productEntity = new ProductEntity(dto, userId);
      groupPurchaseRepository.save(productEntity);
    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success(HttpStatus.OK);
  }
  
}
