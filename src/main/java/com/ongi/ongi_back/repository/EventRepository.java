package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    @Query(value = 
            "SELECT * FROM event " +
            "WHERE is_closed IS FALSE " +
            "ORDER BY event_sequence DESC", nativeQuery = true)
    List<EventEntity> findActiveEvents();

    @Query(value =
            "SELECT * FROM event " +
            "WHERE is_closed IS FALSE " +
            "AND deadline <= NOW()", nativeQuery = true)
    List<EventEntity> findClosingEvents();
    EventEntity findByEventSequence(Integer eventSequence);
}
