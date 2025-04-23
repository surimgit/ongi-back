package com.ongi.ongi_back.common.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.CalendarEntity;

import lombok.Getter;

@Getter
public class CalendarVO {
    private Integer calendarSequence;
    private String calendarTitle;
    private String calendarCategory;
    private String calendarMemo;
    private LocalDateTime calendarStart;
    private LocalDateTime calendarEnd;
    private String calendarRepeat;
    private String color;

    private CalendarVO (CalendarEntity calendarEntity) {
        this.calendarSequence = calendarEntity.getCalendarSequence();
        this.calendarTitle = calendarEntity.getCalendarTitle();
        this.calendarCategory = calendarEntity.getCalendarCategory();
        this.calendarMemo = calendarEntity.getCalendarMemo();
        this.calendarStart = calendarEntity.getCalendarStart();
        this.calendarEnd = calendarEntity.getCalendarEnd();
        this.calendarRepeat = calendarEntity.getCalendarRepeat();
        this.color = calendarEntity.getColor();
    }

    public static List<CalendarVO> getList(List<CalendarEntity> calendarEntities) {
        List<CalendarVO> list = new ArrayList<>();
        for(CalendarEntity calendarEntity: calendarEntities) {
            CalendarVO vo = new CalendarVO(calendarEntity);
            list.add(vo);
        }
        return list;
    }
}
