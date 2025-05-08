package com.ongi.ongi_back.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ongi.ongi_back.common.dto.request.alert.PostAlertRequestDto;
import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.repository.ProductRepository;
import com.ongi.ongi_back.repository.WishListRepository;
import com.ongi.ongi_back.service.AlertService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStatusScheduler {
  
  private final ProductRepository productRepository;
  private final WishListRepository wishListRepository;
  private final AlertService alertService;

  
  @Scheduled(cron = "0 * * * * *")  
  public void updateProductStatues(){

    log.info("상품들의 상태를 체크합니다.");

    List<ProductEntity> products = productRepository.findByOrderBySequenceDesc();
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
    
    for(ProductEntity product: products) {

      LocalDateTime openDate = LocalDateTime.parse(product.getOpenDate(), formatter);
      LocalDateTime deadline = LocalDateTime.parse(product.getDeadline(), formatter);

      String previousStatus = product.getStatus();
      String newStatus;

      Integer remainingProducts = product.getProductQuantity() - product.getBoughtAmount();

      if (remainingProducts == 0 || now.isAfter(deadline)) {
          newStatus = "CLOSE";
      } else if (now.isBefore(openDate)) {
          newStatus = "WAIT";
      } else {
          newStatus = "OPEN";
      }

      product.setStatus(newStatus);

      if("WAIT".equals(previousStatus) && "OPEN".equals(product.getStatus())){
        Integer productSequence = product.getSequence();
        List<String> userIdList = wishListRepository.findUserIdByProductSequence(productSequence);

        for(String userId: userIdList){
          PostAlertRequestDto alertRequestDto = new PostAlertRequestDto(
            "wish_open",
            "system",
            userId,
            productSequence,
            null
          );

          alertService.postAlert(alertRequestDto);
        }

      }
    }

    
    productRepository.saveAll(products);
  }
}
