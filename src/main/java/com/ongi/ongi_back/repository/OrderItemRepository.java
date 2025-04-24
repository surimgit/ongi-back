package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.OrderItemEntity;

import jakarta.transaction.Transactional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
  OrderItemEntity findByPaymentKeyAndProductSequence(String paymentKey, Integer productSequence);
  
  @Transactional
  void deleteByPaymentKeyAndProductSequence(String paymentKey, Integer productSequence);
}
