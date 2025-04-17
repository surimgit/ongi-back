package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.entity.pk.LikedPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="liked")
@Table(name="liked")
@IdClass(LikedPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikedEntity {
    @Id
    private String userId;
    @Id
    private Integer likedPostSequence;
}
