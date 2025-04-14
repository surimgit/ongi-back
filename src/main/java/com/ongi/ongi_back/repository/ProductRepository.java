package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  ProductEntity findByUserIdAndSequence(String userId, Integer productNumber);
  List<ProductEntity> findByOrderBySequenceDesc();
}
