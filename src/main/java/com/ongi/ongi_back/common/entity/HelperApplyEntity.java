package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "HelperApplyEntity")
@Table(name = "helper_apply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelperApplyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applySequence;
    private Integer postSequence;
    private String requesterId;
    private String applicantId;
    private LocalDateTime requestedAt;
    private LocalDateTime acceptedAt;
    private String solutionRequestedAt;
    private Integer chatSequence;
    private Boolean isApplied;

    public HelperApplyEntity(Integer postSequence, NeedHelperEntity post, String requesterId, String applicantId, Integer chatSequence) {
        this.postSequence = postSequence;
        this.requesterId = requesterId;
        this.applicantId = applicantId;
        this.chatSequence = chatSequence;
        this.requestedAt = LocalDateTime.now();
        this.acceptedAt = null;
        this.solutionRequestedAt = post.getSchedule();
        this.isApplied = false;
    }

    public void accpetApply(HelperApplyEntity helperApplyEntity){
    this.acceptedAt = LocalDateTime.now();
    this.isApplied = true;
    }
}
