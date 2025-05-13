package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.EventApplyEntity;
import com.ongi.ongi_back.common.entity.pk.EventApplyPk;

@Repository
public interface EventApplyRepository extends JpaRepository<EventApplyEntity, EventApplyPk> {
    
}
