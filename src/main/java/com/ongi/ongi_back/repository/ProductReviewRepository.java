package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ProductReviewEntity;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Integer> {
  
  ProductReviewEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  List<ProductReviewEntity> findByProductSequence(Integer productSequence);
}
