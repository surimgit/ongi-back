package com.ongi.ongi_back.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="chat")
@Table(name="chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatSequence;
    private String requesterId;
    private String applicantId;
    private Integer needHelperSequence;
    private Boolean chatAvailable;

    public void applyChat(){
        this.chatAvailable = true;
    }
    
}
