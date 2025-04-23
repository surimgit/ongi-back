package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  ProductEntity findByUserIdAndSequence(String userId, Integer productNumber);
  ProductEntity findBySequence(Integer sequence);
  
  List<ProductEntity> findByOrderBySequenceDesc();
  List<ProductEntity> findByCategoryOrderBySequenceDesc(String category);
  List<ProductEntity> findByNameContainingOrderBySequenceDesc(String name);
  List<ProductEntity> findByCategoryAndNameContainingOrderBySequenceDesc(String name, String category);
  
  @Query("SELECT p FROM product p WHERE p.sequence IN :sequences ORDER BY sequence DESC")
  List<ProductEntity> findBySequences(List<Integer> sequences);

}
