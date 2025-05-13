package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    List<EventEntity> findAllByOrderByEventSequenceDesc();
    EventEntity findByEventSequence(Integer eventSequence);
}
