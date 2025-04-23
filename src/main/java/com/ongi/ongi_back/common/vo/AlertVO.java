package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.AlertEntity;

import lombok.Getter;

@Getter
public class AlertVO {
    private Integer alertSequence;
    private String alertContent;
    private Integer alertEntitySequence;
    private String alertType;
    private boolean readPara;

    private AlertVO(AlertEntity alertEntity) {
        this.alertSequence = alertEntity.getAlertSequence();
        this.alertContent = alertEntity.getAlertContent();
        this.alertEntitySequence = alertEntity.getAlertEntitySequence();
        this.alertType = alertEntity.getAlertType();
        this.readPara = alertEntity.isReadPara();
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
