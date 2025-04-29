package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CommunityCommentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityCommentEntity, Integer>{
    @Transactional
    void deleteByPostSequence(Integer postSequence);

    @Transactional
    void deleteByCommentSequence(Integer commentSequence);

    CommunityCommentEntity findByCommentSequence(Integer commentSequence);

    List<CommunityCommentEntity> findByPostSequence(Integer postSequence);

    List<CommunityCommentEntity> findByNicknameOrderByCommentSequenceDesc(String nickname);

    Integer countByPostSequence(Integer postSequence);
}
