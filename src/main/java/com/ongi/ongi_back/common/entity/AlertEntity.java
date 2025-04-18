package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.alert.PostAlertRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="alert")
@Table(name="alert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alertSequence;
    private String alertType;
    private String senderId;
    private String receiverId;
    private Integer alertEntitySequence;
    private String alertContent;

    public AlertEntity(PostAlertRequestDto dto) {
        this.alertType = dto.getAlertType();
        this.senderId = dto.getSenderId();
        this.receiverId = dto.getReceiverId();
        this.alertEntitySequence = dto.getAlertEntitySequence();
        this.alertContent = "";
    };
}
