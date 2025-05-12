package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.entity.pk.ReviewImagesPK;
import com.ongi.ongi_back.common.vo.ReviewImagesVO;

@Repository
public interface ReviewImagesRepository extends JpaRepository<ReviewImagesEntity, ReviewImagesPK> {
  @Query(
    "SELECT new com.ongi.ongi_back.common.vo.ReviewImagesVO(pr.reviewSequence, pr.userId, pr.productSequence, ri.reviewImage) " +
    "FROM product_review pr " +
    "JOIN review_images ri ON pr.reviewSequence = ri.reviewSequence " +
    "WHERE pr.productSequence = :sequence"
  )
  List<ReviewImagesVO> findReviewImages(Integer sequence);
}
