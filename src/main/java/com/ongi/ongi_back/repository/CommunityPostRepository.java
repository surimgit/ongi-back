package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CommunityPostEntity;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPostEntity, Integer>{
    CommunityPostEntity findByPostSequence(Integer postSequence);
    List<CommunityPostEntity> findByOrderByPostSequenceDesc();

    List<CommunityPostEntity> findByNicknameOrderByPostSequenceDesc(String nickname);
    List<CommunityPostEntity> findByTitleContainingOrderByPostSequenceDesc(String title);
    List<CommunityPostEntity> findByContentContainingOrderByPostSequenceDesc(String keyword);

    boolean existsByPostSequence(Integer postSequence);
}
