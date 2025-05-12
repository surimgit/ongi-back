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
public class HelperLikedPk implements Serializable {
    @Column(name="user_id")
    private String userId;
    @Column(name="helper_liked")
    private Integer likedPostSequence;
}
