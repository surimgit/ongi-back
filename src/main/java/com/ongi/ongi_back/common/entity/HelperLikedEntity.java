package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.entity.pk.HelperLikedPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="helperLiked")
@Table(name="helper_liked")
@IdClass(HelperLikedPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelperLikedEntity {
    @Id
    private String userId;
    @Id
    private Integer likedPostSequence;
}
