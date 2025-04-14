package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CommunityCommentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityCommentEntity, Integer>{
    @Transactional
    void deleteByPostSequence(Integer postSequence);
}
