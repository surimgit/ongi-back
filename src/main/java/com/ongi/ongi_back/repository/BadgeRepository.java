package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.BadgeEntity;
import com.ongi.ongi_back.common.entity.pk.BadgePk;

@Repository
public interface BadgeRepository extends JpaRepository<BadgeEntity, BadgePk>{
  List<BadgeEntity> findAllByUserId(String userId);
  BadgeEntity findByUserIdAndBadge(String userId, String Badge);
}
