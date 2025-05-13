package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.EventEntity;

import lombok.Getter;

@Getter
public class EventVO {
    private Integer eventSequence;
    private String title;
    private String deadline;
    private Integer neededPoint;
    private String content;
    private String image;

    private EventVO(EventEntity eventEntity) {
        this.eventSequence = eventEntity.getEventSequence();
        this.title = eventEntity.getTitle();
        this.deadline = eventEntity.getDeadline();
        this.neededPoint = eventEntity.getNeededPoint();
        this.content = eventEntity.getContent();
        this.image = eventEntity.getImage();
    }

    public static List<EventVO> getList(List<EventEntity> eventEntities) {
        List<EventVO> list = new ArrayList<>();

        for (EventEntity eventEntity: eventEntities) {
            EventVO vo = new EventVO(eventEntity);
            list.add(vo);
        }

        return list;
    }
}
