package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.AlertEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AlertRespository extends JpaRepository<AlertEntity, Integer>{
    List<AlertEntity> findByReceiverIdOrderByAlertSequenceDesc(String userId);
    @Query(value = "SELECT * FROM alert " +
                    "WHERE receiver_id = :receiverId " +
                    "AND read_para = FALSE", nativeQuery=true)
    List<AlertEntity> findNotReadAlerts(String receiverId);
    AlertEntity findByAlertSequence(Integer alertSequence);

    @Transactional
    void deleteByAlertSequence(Integer alertSequence);

    @Transactional
    void deleteByReceiverId(String userId);
}
