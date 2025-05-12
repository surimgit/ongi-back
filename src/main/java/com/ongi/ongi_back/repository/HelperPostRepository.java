package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;

public interface HelperPostRepository extends JpaRepository<NeedHelperEntity, Integer> {
        NeedHelperEntity findBySequence(Integer sequence);

        @Query("SELECT new com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto(" +
                "h, h.userId) FROM NeedHelperEntity h ORDER BY h.sequence DESC")
        List<GetHelperPostResponseDto> findAllWithNickname();

        @Query("SELECT new com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto(" +
                "h, h.userId) FROM NeedHelperEntity h WHERE h.sequence = :sequence")
        GetHelperPostResponseDto findBySequenceWithNickname(@Param("sequence") Integer sequence);

        boolean existsBySequence(Integer sequence);

        Integer countByUserId(String userId); 
}