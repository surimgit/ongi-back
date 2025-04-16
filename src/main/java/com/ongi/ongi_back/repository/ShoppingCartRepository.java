package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CartEntity;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartEntity, Integer> {
  CartEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  List<CartEntity> findByUserId(String userId);
  CartEntity findByUserIdAndShoppingCartSequence(String userId, Integer shoppingCartSequence);
  CartEntity findByShoppingCartSequenceAndUserId(Integer shoppingCartSequence, String userId);
}
