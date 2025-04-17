package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.NoticeEntity;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>{
  NoticeEntity findBySequence(Integer sequence);
  List<NoticeEntity> findAllByOrderBySequenceDesc();
}
