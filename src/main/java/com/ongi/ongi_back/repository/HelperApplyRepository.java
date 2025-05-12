package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.HelperApplyEntity;

import jakarta.transaction.Transactional;

@Repository
public interface HelperApplyRepository extends JpaRepository<HelperApplyEntity, Integer>{

    HelperApplyEntity findByPostSequenceAndApplicantId(Integer postSequence, String applicantId);
    boolean existsByPostSequenceAndApplicantId(Integer postSequence, String applicantId);

    @Transactional
    void deleteByPostSequenceAndApplicantId(Integer postSequence, String applicantId);
    
}
