package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.LikeKeywordEntity;
import com.ongi.ongi_back.common.entity.pk.LikeKeywordPk;
@Repository
public interface LikeKeywordRepository extends JpaRepository<LikeKeywordEntity, LikeKeywordPk>{
  LikeKeywordEntity findByUserIdAndKeyword(String userId, String keyword);
  LikeKeywordEntity findByUserId(String userId);
  List<LikeKeywordEntity> findAllByUserId(String userId);
}
