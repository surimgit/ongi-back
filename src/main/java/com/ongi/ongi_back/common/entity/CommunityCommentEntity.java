package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="communityComment")
@Table(name="community_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer commentSequence;
    private Integer postSequence;
    private String userId;
    private String nickname;
    private String content;
    private String postDate;

    public CommunityCommentEntity(PostCommentRequestDto dto, Integer postSequence ,String userId, String nickname) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.userId = userId;
        this.nickname = nickname;
        this.postSequence = postSequence;
        this.content = dto.getContent();
        this.postDate = now.format(dateTimeFormatter);
    }
}
