package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ChatEntity;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    boolean existsByRequesterIdAndApplicantId(String requesterId, String applicantId);
    List<ChatEntity> findByRequesterIdOrApplicantId(String requesterId, String applicantId);
    @Query("SELECT c FROM chat c WHERE c.requesterId = :requesterId AND c.applicantId = :applicantId AND c.needHelperSequence = :needHelperSequence")
    ChatEntity findByRequesterIdAndApplicantIdAndNeedHelperSequence(
            @Param("needHelperSequence") Integer needHelperSequence,
            @Param("requesterId") String requesterId,
            @Param("applicantId") String applicantId
            );    

    void  deleteByNeedHelperSequenceAndApplicantId(Integer postSequence, String applicantId);
    ChatEntity findByRequesterIdAndChatSequence(String requesterId, Integer chatSequence);
}
