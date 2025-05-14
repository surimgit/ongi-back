package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ChatEntity;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    boolean existsByRequesterIdAndApplicantId(String requesterId, String applicantId);
    List<ChatEntity> findByRequesterIdOrApplicantId(String requesterId, String applicantId);
    ChatEntity findByRequesterIdAndApplicantId(String requesterId, String applicantId);
}
