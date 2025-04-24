package com.ongi.ongi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.UserEntity;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    boolean existsByUserId(String userId);

    String findNicknameByUserId(String userId);
    UserEntity findByUserId(String userId);
    UserEntity findByTelNumber(String telNumber);

    UserEntity findByIsAdminTrue();

}