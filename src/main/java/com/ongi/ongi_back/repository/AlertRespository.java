package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.AlertEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AlertRespository extends JpaRepository<AlertEntity, Integer>{
    List<AlertEntity> findByReceiverIdOrderByAlertSequenceDesc(String userId);
    AlertEntity findByAlertSequence(Integer alertSequence);

    @Transactional
    void deleteByAlertSequence(Integer alertSequence);

    @Transactional
    void deleteByReceiverId(String userId);
}
