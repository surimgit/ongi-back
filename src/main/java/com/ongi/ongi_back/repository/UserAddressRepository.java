package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.UserAddressEntity;
import com.ongi.ongi_back.common.vo.UserAddressVO;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer> {

  UserAddressEntity findByUserIdAndAddressLabel(String userId, String addressLabel);


  @Query("SELECT new com.ongi.ongi_back.common.vo.UserAddressVO(ua.id, ua.addressLabel)  FROM user_address ua WHERE ua.userId = :userId")
  List<UserAddressVO> findAddressLabelByUserId(String userId);

  UserAddressEntity findByUserIdAndId(String userId, Integer id);
}
