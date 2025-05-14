package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.StockReservationEntity;
import com.ongi.ongi_back.common.vo.StockReservationVO;

import jakarta.transaction.Transactional;

@Repository
public interface StockReservationRepository extends JpaRepository<StockReservationEntity, Integer> {
  
  @Query("SELECT COALESCE(SUM(s.quantity), 0) FROM stock_reservation s WHERE s.productSequence = :productSequence")
  Integer sumQuantityByProductSequence(Integer productSequence);
  StockReservationEntity findByUserIdAndProductSequence(String userId, Integer productSequence);
  List<StockReservationVO> findByProductSequence(Integer productSequence);
  List<StockReservationEntity> findByUserId(String userId);
  
  @Transactional
  void deleteByUserIdAndProductSequence(String userId, Integer productSequence);
}
