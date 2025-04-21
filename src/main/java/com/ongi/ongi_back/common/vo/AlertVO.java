package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.AlertEntity;

import lombok.Getter;

@Getter
public class AlertVO {
    private String alertContent;
    private Integer alertEntitySequence;

    private AlertVO(AlertEntity alertEntity) {
        this.alertContent = alertEntity.getAlertContent();
        this.alertEntitySequence = alertEntity.getAlertEntitySequence();
    }

    public static List<AlertVO> getList(List<AlertEntity> alertEntities) {
        List<AlertVO> list = new ArrayList<>();

        for (AlertEntity alertEntity: alertEntities) {
            AlertVO vo = new AlertVO(alertEntity);
            list.add(vo);
        }

        return list;
    }
}
