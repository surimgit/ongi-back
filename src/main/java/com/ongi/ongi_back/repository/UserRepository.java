package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    boolean existsByUserId(String userId);

    String findNicknameByUserId(String userId);
    UserEntity findByUserId(String userId);
    UserEntity findByTelNumber(String telNumber);
    
    UserEntity findByIsAdminTrue();

    UserEntity findByNicknameAndTelNumber(String nickname, String telNumber);
    UserEntity findByUserIdAndTelNumber(String userId, String telNumber);
    Integer countAllByIsResigned(boolean isResigned);

    @Query(value = 
            "SELECT * FROM user " +
            "WHERE is_admin = true " +
            "LIMIT 1", nativeQuery = true)
    UserEntity findAdminAccount();
}