package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.HelperLikedEntity;
import com.ongi.ongi_back.common.entity.pk.HelperLikedPk;

@Repository
public interface HelperLikedRepository extends JpaRepository<HelperLikedEntity, HelperLikedPk> {
    HelperLikedEntity findByUserIdAndLikedPostSequence(String userId, Integer postSequence);

    List<HelperLikedEntity> findByLikedPostSequence(Integer postSequence);
    List<HelperLikedEntity> findByUserId(String userId);    
}
