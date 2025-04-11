package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongi.ongi_back.common.entity.CommunityPostEntity;

public interface CommunityPostRepository extends JpaRepository<CommunityPostEntity, Integer>{
    
}
