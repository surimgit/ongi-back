package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.PaymentConfirmEntity;

@Repository
public interface PaymentConformRepository extends JpaRepository<PaymentConfirmEntity, String> {
  PaymentConfirmEntity findByPaymentKey(String paymentKey);
}
