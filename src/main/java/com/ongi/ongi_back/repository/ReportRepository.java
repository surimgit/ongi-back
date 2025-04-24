package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    List<ReportEntity> findByReportProcessIsNullOrderByReportSequenceDesc();
    List<ReportEntity> findByReportProcessIsNotNullOrderByReportSequenceDesc();
}
