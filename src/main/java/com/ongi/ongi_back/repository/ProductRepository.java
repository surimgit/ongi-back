package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ProductEntity;
import com.ongi.ongi_back.common.vo.MySalesVO;
import com.ongi.ongi_back.common.vo.ProductVO;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  ProductEntity findByUserIdAndSequence(String userId, Integer productNumber);
  ProductEntity findBySequence(Integer sequence);
  List<ProductEntity> findByOrderBySequenceDesc();

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ProductVO(" +
    "p.sequence, p.name, p.category, p.price, p.purchasedPeople, " +
    "p.deadline, p.boughtAmount, p.productQuantity, " +
    "p.image, p.openDate, p.status, " +
    "COALESCE(AVG(pr.rating), 0) as rating, COUNT(pr) as reviewCount) " + 
    "FROM product p " +
    "LEFT JOIN product_review pr ON p.sequence = pr.productSequence " +
    "GROUP BY p.name, p.sequence, p.userId, p.price, p.category, " +
    "p.productQuantity, p.boughtAmount, p.purchasedPeople, " +
    "p.deadline, p.adPayment, p.openDate, p.image, p.status " +
    "ORDER BY p.sequence DESC")
  List<ProductVO> findProducts();

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ProductVO(" +
    "p.sequence, p.name, p.category, p.price, p.purchasedPeople, " +
    "p.deadline, p.boughtAmount, p.productQuantity, " +
    "p.image, p.openDate, p.status, " +
    "COALESCE(AVG(pr.rating), 0) as rating, COUNT(pr) as reviewCount) " + 
    "FROM product p " +
    "LEFT JOIN product_review pr ON p.sequence = pr.productSequence " +
    "WHERE p.category = :category " +
    "GROUP BY p.name, p.sequence, p.userId, p.price, p.category, " +
    "p.productQuantity, p.boughtAmount, p.purchasedPeople, " +
    "p.deadline, p.adPayment, p.openDate, p.image, p.status " +
    "ORDER BY p.sequence DESC")
  List<ProductVO> findByCategoryOrderBySequenceDesc(String category);

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ProductVO(" +
    "p.sequence, p.name, p.category, p.price, p.purchasedPeople, " +
    "p.deadline, p.boughtAmount, p.productQuantity, " +
    "p.image, p.openDate, p.status, " +
    "COALESCE(AVG(pr.rating), 0) as rating, COUNT(pr) as reviewCount) " + 
    "FROM product p " +
    "LEFT JOIN product_review pr ON p.sequence = pr.productSequence " +
    "WHERE p.name LIKE %:name% " +
    "GROUP BY p.name, p.sequence, p.userId, p.price, p.category, " +
    "p.productQuantity, p.boughtAmount, p.purchasedPeople, " +
    "p.deadline, p.adPayment, p.openDate, p.image, p.status " +
    "ORDER BY p.sequence DESC")
  List<ProductVO> findByNameContainingOrderBySequenceDesc(String name);

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ProductVO(" +
    "p.sequence, p.name, p.category, p.price, p.purchasedPeople, " +
    "p.deadline, p.boughtAmount, p.productQuantity, " +
    "p.image, p.openDate, p.status, " +
    "COALESCE(AVG(pr.rating), 0) as rating, COUNT(pr) as reviewCount) " + 
    "FROM product p " +
    "LEFT JOIN product_review pr ON p.sequence = pr.productSequence " +
    "WHERE p.category = :category AND p.name LIKE %:name% " +
    "GROUP BY p.name, p.sequence, p.userId, p.price, p.category, " +
    "p.productQuantity, p.boughtAmount, p.purchasedPeople, " +
    "p.deadline, p.adPayment, p.openDate, p.image, p.status " +
    "ORDER BY p.sequence DESC")

  List<ProductVO> findByCategoryAndNameContainingOrderBySequenceDesc(String name, String category);
  
  @Query("SELECT p FROM product p WHERE p.sequence IN :sequences ORDER BY sequence DESC")
  List<ProductEntity> findBySequences(List<Integer> sequences);

  List<ProductEntity> findAllByUserIdOrderBySequenceDesc(String userId);
  List<ProductEntity> findByUserId(String userId);

  @Query("SELECT p FROM product p WHERE p.userId = :userId AND p.deadline > :today ORDER BY p.sequence DESC")
  List<ProductEntity> findByUserIdAndDeadlineAfterNow(String userId, String today);

  @Query("SELECT p FROM product p WHERE p.userId = :userId AND p.deadline < :today ORDER BY p.sequence DESC")
  List<ProductEntity> findByUserIdAndDeadlineBeforeNow(String userId, String today);

}
