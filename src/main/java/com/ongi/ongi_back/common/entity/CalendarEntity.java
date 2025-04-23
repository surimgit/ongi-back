package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;

import com.ongi.ongi_back.common.dto.request.calendar.PatchCalendarRequestDto;
import com.ongi.ongi_back.common.dto.request.calendar.PostScheduleRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="calendar")
@Table(name="calendar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer calendarSequence;
    
    private String userId;
    private String calendarTitle;
    private String calendarCategory;
    private String calendarMemo;
    private LocalDateTime calendarStart;
    private LocalDateTime calendarEnd;
    private String calendarRepeat;
    private String color;

    public CalendarEntity(PostScheduleRequestDto dto, String userId) {
        this.userId = userId;
        this.calendarTitle = dto.getCalendarTitle();
        this.calendarCategory = dto.getCalendarCategory();
        this.calendarMemo = dto.getCalendarMemo();
        this.calendarStart = dto.getCalendarStart();
        this.calendarEnd = dto.getCalendarEnd();
        this.calendarRepeat = dto.getCalendarRepeat();
        this.color = dto.getColor();
    }

    public void patch(PatchCalendarRequestDto dto){
        this.calendarTitle = dto.getCalendarTitle();
        this.calendarCategory = dto.getCalendarCategory();
        this.calendarMemo = dto.getCalendarMemo();
        this.calendarStart = dto.getCalendarStart();
        this.calendarEnd = dto.getCalendarEnd();
        this.calendarRepeat = dto.getCalendarRepeat();
        this.color = dto.getColor();
    }
}
