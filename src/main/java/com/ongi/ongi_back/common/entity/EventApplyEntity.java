package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.event.PostEventApplyRequestDto;
import com.ongi.ongi_back.common.entity.pk.EventApplyPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="event_apply")
@Table(name="event_apply")
@IdClass(EventApplyPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventApplyEntity {
    @Id
    private String userId;
    @Id
    private Integer appliedEventSequence;
    private boolean isAwarded;

    public EventApplyEntity(PostEventApplyRequestDto dto) {
        this.userId = dto.getUserId();
        this.appliedEventSequence = dto.getEventSequence();
    }
}
