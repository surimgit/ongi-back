package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.CommunityPostEntity;



@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPostEntity, Integer>{
    CommunityPostEntity findByPostSequence(Integer postSequence);

    List<CommunityPostEntity> findByOrderByPostSequenceDesc();
    List<CommunityPostEntity> findByBoardOrderByPostSequenceDesc(String board);
    List<CommunityPostEntity> findByCategoryOrderByPostSequenceDesc(String category);
    List<CommunityPostEntity> findByHotPostTrueOrderByPostSequenceDesc();
    @Query(value = "SELECT * FROM community_post " +
                    "WHERE board = '우리 동네 게시판' " +
                    "AND county LIKE CONCAT(:county, '%') " +
                    "ORDER BY post_sequence DESC", nativeQuery = true)
    List<CommunityPostEntity> findCountyPosts(@Param("county") String county);
    @Query(value = "SELECT * FROM community_post " +
                    "WHERE board = '우리 동네 게시판' " +
                    "AND county LIKE CONCAT(:county, '%')" +
                    "AND category = :category " +
                    "ORDER BY post_sequence DESC", nativeQuery = true)
    List<CommunityPostEntity> findCountyCategoryPosts(@Param("county") String county, @Param("category") String category);

    List<CommunityPostEntity> findByNicknameOrderByPostSequenceDesc(String nickname);
    List<CommunityPostEntity> findByTitleContainingOrderByPostSequenceDesc(String title);
    List<CommunityPostEntity> findByContentContainingOrderByPostSequenceDesc(String keyword);

    List<CommunityPostEntity> findAllByPostSequenceInOrderByPostSequenceDesc(List<Integer> postSequence);

    boolean existsByPostSequence(Integer postSequence);

    @Query(value = "SELECT * FROM community_post " +
                   "WHERE board IN ('정보 게시판', '우리 동네 게시판') " +
                   "AND post_date >= NOW() - INTERVAL 1 DAY " +
                   "AND liked >= 1 " +
                   "ORDER BY liked DESC, view_count DESC " +
                   "LIMIT 10", nativeQuery = true)
    List<CommunityPostEntity> findTop10RecentPopularPosts();
}
