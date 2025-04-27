package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.dto.response.user.GetMyBuyingResponseDto;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.vo.MyBuyingVO;

import jakarta.transaction.Transactional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
  OrderItemEntity findByPaymentKeyAndProductSequence(String paymentKey, Integer productSequence);
  
  @Query(
  "SELECT new com.ongi.ongi_back.common.vo.MyBuyingVO(o.orderItemSequence, pr.name, pr.image, o.quantity, pr.price, p.approvedTime) " +
  "FROM order_item o " +
  "JOIN payment_confirm p ON o.paymentKey = p.paymentKey " +
  "JOIN payment_order po ON p.orderId = po.orderId " +
  "JOIN product pr ON o.productSequence = pr.sequence " +
  "WHERE po.userId = :userId")
List<MyBuyingVO> findMyBuyingList(@Param("userId") String userId);



  @Transactional
  void deleteByPaymentKeyAndProductSequence(String paymentKey, Integer productSequence);
}
