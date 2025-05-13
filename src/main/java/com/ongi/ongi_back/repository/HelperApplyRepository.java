package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.HelperApplyEntity;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;

@Repository
public interface HelperApplyRepository extends JpaRepository<HelperApplyEntity, Integer>{

    HelperApplyEntity findByPostSequenceAndApplicantId(Integer postSequence, String applicantId);
    
    boolean existsByPostSequenceAndApplicantId(Integer postSequence, String applicantId);
    @Transactional
    void deleteByPostSequenceAndApplicantId(Integer postSequence, String applicantId);
    
    @Query("SELECT h.postSequence FROM HelperApplyEntity h WHERE h.applicantId = :applicantId")
    List<Integer> findPostSequenceByApplicantId(@Param("applicantId") String applicantId);

    Integer countByPostSequence(Integer postSequence);

    List<HelperApplyEntity> findByPostSequenceAndRequesterId(Integer postSequence, String requesterId);
}
