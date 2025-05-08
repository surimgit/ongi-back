package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.PostImageEntity;

@Repository
public interface PostImageRepository extends JpaRepository<PostImageEntity, Integer>{
    
}
