package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ReviewImagesEntity;
import com.ongi.ongi_back.common.entity.pk.ReviewImagesPK;

@Repository
public interface ReviewImagesRepository extends JpaRepository<ReviewImagesEntity, ReviewImagesPK> {
  
}
