package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ShoppingCartEntity;
import com.ongi.ongi_back.common.vo.ShoppingCartVO;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {
  ShoppingCartEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  // List<ShoppingCartEntity> findByUserId(String userId);
  ShoppingCartEntity findByUserIdAndShoppingCartSequence(String userId, Integer shoppingCartSequence);
  Integer countByUserId(String userId);

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ShoppingCartVO(s.shoppingCartSequence, s.productSequence, s.quantity, p.name, p.price, p.image) " +
    "FROM shopping_cart s " +
    "LEFT JOIN product p ON s.productSequence = p.sequence " +
    "WHERE s.userId = :userId")
  List<ShoppingCartVO> findCartDetails(@Param("userId") String userId);


  ShoppingCartEntity findByShoppingCartSequenceAndUserId(Integer shoppingCartSequence, String userId);

}
