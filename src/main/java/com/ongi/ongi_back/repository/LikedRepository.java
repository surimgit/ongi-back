package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.common.entity.pk.LikedPk;

@Repository
public interface LikedRepository extends JpaRepository<LikedEntity, LikedPk> {
    LikedEntity findByUserIdAndLikedPostSequence(String userId, Integer postSequence);

    List<LikedEntity> findByLikedPostSequence(Integer postSequence);
    List<LikedEntity> findByUserId(String userId);
}
