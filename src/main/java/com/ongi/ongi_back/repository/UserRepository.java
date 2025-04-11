package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongi.ongi_back.common.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

}
