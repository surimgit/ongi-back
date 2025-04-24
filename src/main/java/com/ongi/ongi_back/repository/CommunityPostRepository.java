package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CommunityPostEntity;



@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPostEntity, Integer>{
    CommunityPostEntity findByPostSequence(Integer postSequence);

    List<CommunityPostEntity> findByOrderByPostSequenceDesc();
    List<CommunityPostEntity> findByBoardOrderByPostSequenceDesc(String board);
    List<CommunityPostEntity> findByCategoryOrderByPostSequenceDesc(String category);
    List<CommunityPostEntity> findByHotPostTrueOrderByPostSequenceDesc();

    List<CommunityPostEntity> findByNicknameOrderByPostSequenceDesc(String nickname);
    List<CommunityPostEntity> findByTitleContainingOrderByPostSequenceDesc(String title);
    List<CommunityPostEntity> findByContentContainingOrderByPostSequenceDesc(String keyword);

    List<CommunityPostEntity> findAllByPostSequenceInOrderByPostSequenceDesc(List<Integer> postSequence);

    boolean existsByPostSequence(Integer postSequence);
}
