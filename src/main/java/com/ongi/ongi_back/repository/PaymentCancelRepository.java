package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.PaymentCancelEntity;

@Repository
public interface PaymentCancelRepository extends JpaRepository<PaymentCancelEntity, String> {
  
}
