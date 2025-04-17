package com.ongi.ongi_back.common.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikedPk implements Serializable {
    @Column(name="user_id")
    private String userId;
    @Column(name="liked_post_sequence")
    private Integer likedPostSequence;
}
