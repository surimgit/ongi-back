package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.AlertEntity;

@Repository
public interface AlertRespository extends JpaRepository<AlertEntity, Integer>{
    List<AlertEntity> findByReceiverId(String userId);
}
