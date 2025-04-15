package com.ongi.ongi_back.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongi.ongi_back.common.entity.VerificationCodeEntity;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodeEntity, Integer> {
    Optional<VerificationCodeEntity> findByTelNumber(String telNumber);
    Optional<VerificationCodeEntity> findByTelNumberAndCode(String telNumber, String code);
    Optional<VerificationCodeEntity> deleteAllByExpiryTimeBefore(LocalDateTime expiryTime);
}
