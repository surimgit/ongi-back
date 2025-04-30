package com.ongi.ongi_back.common.util.enums;

import lombok.Getter;

@Getter
public enum JobCdEnum {
    EMPLOYED("0013001", "재직자"),
    SELF_EMPLOYED("0013002", "자영업자"),
    UNEMPLOYED("0013003", "미취업자"),
    FREELANCER("0013004", "프리랜서"),
    DAILY_WORKER("0013005", "일용근로자"),
    STARTUP_PREPARER("0013006", "(예비)창업자"),
    SHORT_TERM_WORKER("0013007", "단기근로자"),
    AGRICULTURE_WORKER("0013008", "영농종사자"),
    ETC("0013009", "기타"),
    NO_LIMIT("0013010", "제한없음");

    private final String code;
    private final String description;

    JobCdEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        for (JobCdEnum value : values()) {
            if (value.code.equals(code)) {
                return value.description;
            }
        }
        return "알 수 없음";
    }
}