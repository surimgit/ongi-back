package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.community.PostCommunityPostRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="communityPost")
@Table(name="community_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer postSequence;
    private String userId;
    private String postDate;
    private String category;
    private String title;
    private String content;
    private Integer liked;

    public CommunityPostEntity(PostCommunityPostRequestDto dto, String userId) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.userId = userId;
        this.postDate = now.format(dateTimeFormatter);
        this.category = dto.getCategory();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.liked = 0;
    }
}
