package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.PaymentOrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<PaymentOrderEntity, String> {
  
}
