package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.WishListEntity;
import com.ongi.ongi_back.common.entity.pk.WishListPk;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, WishListPk> {
  WishListEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  List<WishListEntity> findByUserId(String userId);
}
