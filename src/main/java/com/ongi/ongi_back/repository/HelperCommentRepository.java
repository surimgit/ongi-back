package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface HelperCommentRepository extends JpaRepository<NeedHelperCommentEntity, Integer>{
    @Transactional
    void deleteByPostSequence(Integer postSequence);

    @Transactional
    void deleteByCommentSequence(Integer commentSequence);

    NeedHelperCommentEntity findByCommentSequence(Integer commentSequence);

    List<NeedHelperCommentEntity> findByPostSequence(Integer postSequence);

    List<NeedHelperCommentEntity> findByNicknameOrderByCommentSequenceDesc(String nickname);

    Integer countByPostSequence(Integer postSequence);

    Integer countByUserId(String userId);
}
