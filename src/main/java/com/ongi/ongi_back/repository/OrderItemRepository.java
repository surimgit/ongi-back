package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto;
import com.ongi.ongi_back.common.entity.OrderItemEntity;
import com.ongi.ongi_back.common.vo.MyBuyingVO;
import com.ongi.ongi_back.common.vo.OrderItemVO;

import jakarta.transaction.Transactional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
  OrderItemEntity findByPaymentKeyAndOrderItemSequence(String paymentKey, Integer productSequence);
  OrderItemEntity findByOrderItemSequence(Integer sequence);

  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.OrderItemVO(o.orderItemSequence, o.productSequence, o.quantity, o.waybillNumber, p.approvedTime, o.buyerId, o.addressId) " +
    "FROM order_item o " +
    "JOIN payment_confirm p ON o.paymentKey = p.paymentKey " +
    "WHERE o.productSequence = :productSequence"
  )
  List<OrderItemVO> findByProductSequence(Integer productSequence);
  
  @Query(
  "SELECT new com.ongi.ongi_back.common.vo.MyBuyingVO(o.paymentKey, o.orderItemSequence, o.productSequence, pr.name, pr.image, o.quantity, pr.price, p.approvedTime, o.waybillNumber, o.addressId, po.userId) " +
  "FROM order_item o " +
  "JOIN payment_confirm p ON o.paymentKey = p.paymentKey " +
  "JOIN payment_order po ON p.orderId = po.orderId " +
  "JOIN product pr ON o.productSequence = pr.sequence " +
  "WHERE po.userId = :userId")
  List<MyBuyingVO> findMyBuyingList(@Param("userId") String userId);

  @Query("""
        SELECT new com.ongi.ongi_back.common.dto.request.payment.PostCancelRequestDto(
          oi.paymentKey,
          "상품 삭제",
          oi.quantity * p.price,
          p.sequence,
          oi.orderItemSequence
        )
        FROM order_item oi
        JOIN product p ON oi.productSequence = p.sequence
        WHERE p.sequence = :sequence
    """)
  List<PostCancelRequestDto> findByCancelInfo(Integer sequence);

  @Transactional
  void deleteByPaymentKeyAndOrderItemSequence(String paymentKey, Integer orderItemSequence);
}
