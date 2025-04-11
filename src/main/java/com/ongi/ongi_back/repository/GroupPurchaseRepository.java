package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.entity.UserEntity;

@Repository
public interface GroupPurchaseRepository extends JpaRepository<ProductEntity, Integer> {
  UserEntity findByUserId(String userId);
}
