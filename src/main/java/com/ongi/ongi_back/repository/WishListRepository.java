package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.WishListEntity;
import com.ongi.ongi_back.common.entity.pk.WishListPk;
import com.ongi.ongi_back.common.vo.WishVO;

import org.springframework.data.repository.query.Param;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, WishListPk> {
  WishListEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  List<String> findUserIdByProductSequence(Integer productSequence);
  long countByUserId(String userId);

  @Query("SELECT new com.ongi.ongi_back.common.vo.WishVO( " +
  "p.name, p.price, p.sequence, " +
  "(p.productQuantity - p.boughtAmount), p.deadline, p.status, p.openDate, p.image) " +
  "FROM wish_list w JOIN product p " +
  "ON w.productSequence = p.sequence " +
  "WHERE w.userId = :userId")
  List<WishVO> findByUserId(@Param("userId") String userId);

}
