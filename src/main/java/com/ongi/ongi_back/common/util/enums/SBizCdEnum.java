package com.ongi.ongi_back.common.util.enums;

import lombok.Getter;

@Getter
public enum SBizCdEnum {
    SMALL_BUSINESS("0014001", "중소기업"),
    WOMEN("0014002", "여성"),
    LOW_INCOME("0014003", "기초생활수급자"),
    SINGLE_PARENT("0014004", "한부모가정"),
    DISABLED("0014005", "장애인"),
    FARMER("0014006", "농업인"),
    SOLDIER("0014007", "군인"),
    REGIONAL_TALENT("0014008", "지역인재"),
    ETC("0014009", "기타"),
    NO_LIMIT("0014010", "제한없음");

    private final String code;
    private final String description;

    SBizCdEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        for (SBizCdEnum value : values()) {
            if (value.code.equals(code)) {
                return value.description;
            }
        }
        return "알 수 없음";
    }
}