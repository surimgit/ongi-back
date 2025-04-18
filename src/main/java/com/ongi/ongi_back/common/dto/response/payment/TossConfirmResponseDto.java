package com.ongi.ongi_back.common.dto.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossConfirmResponseDto {
    private String status;
    private String approvedAt;
    private Failure failure;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Failure {
        private String code;
        private String message;
    }
}