package com.ongi.ongi_back.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStatusScheduler {
  
  private final ProductRepository productRepository;

  
  @Scheduled(cron = "0 0 * * * *")
  public void updateProductStatues(){

    List<ProductEntity> products = productRepository.findByOrderBySequenceDesc();
    LocalDateTime now = LocalDateTime.now();

    for(ProductEntity product: products) {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
      LocalDateTime openDate = LocalDateTime.parse(product.getOpenDate(), formatter);
      LocalDateTime deadline = LocalDateTime.parse(product.getDeadline(), formatter);

      if(now.isBefore(openDate)){
        product.setStatus("WAIT");
      }else if(now.isAfter(deadline)) {
        product.setStatus("CLOSE");
      }
      else{
        product.setStatus("OPEN");
      }
    }
  }
}
