package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.event.PostEventRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="event")
@Table(name="event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventSequence;
    private String title;
    private String deadline;
    private Integer neededPoint;
    private String content;
    private String image;
    private boolean isClosed;

    public EventEntity(PostEventRequestDto dto) {
        this.title = dto.getTitle();
        this.deadline = dto.getDeadline();
        this.neededPoint = dto.getNeededPoint();
        this.content = dto.getContent();
        this.image = dto.getImage();
    }
}
