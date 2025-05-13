package com.ongi.ongi_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ongi.ongi_back.common.entity.UserEntity;

@Repository
public interface  UserRankRepository extends JpaRepository<UserEntity, String> {
    @Query(value = """
    SELECT 
        u.user_id,
        u.nickname,
        COALESCE(p.post_count, 0) + COALESCE(c.comment_count, 0) AS total_activity
    FROM user u
    LEFT JOIN (
        SELECT user_id, COUNT(*) AS post_count
        FROM community_post
        GROUP BY user_id
    ) p ON u.user_id = p.user_id
    LEFT JOIN (
        SELECT user_id, COUNT(*) AS comment_count
        FROM community_comment
        GROUP BY user_id
    ) c ON u.user_id = c.user_id
    ORDER BY total_activity DESC
""", nativeQuery = true)
    List<Object[]> findCommunityUserActivityRankRaw();

    @Query(value = """
    SELECT 
        u.user_id,
        u.nickname,
        COALESCE(p.post_count, 0) + COALESCE(c.comment_count, 0) AS total_activity
    FROM user u
    LEFT JOIN (
        SELECT user_id, COUNT(*) AS post_count
        FROM need_helper
        GROUP BY user_id
    ) p ON u.user_id = p.user_id
    LEFT JOIN (
        SELECT user_id, COUNT(*) AS comment_count
        FROM helper_comment
        GROUP BY user_id
    ) c ON u.user_id = c.user_id
    ORDER BY total_activity DESC
""", nativeQuery = true)
    List<Object[]> findHelperUserActivityRankRaw();
}
