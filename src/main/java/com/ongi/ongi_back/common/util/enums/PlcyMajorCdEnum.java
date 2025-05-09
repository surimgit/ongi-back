package com.ongi.ongi_back.common.util.enums;

import lombok.Getter;

@Getter
public enum PlcyMajorCdEnum {
    HUMANITIES("0011001", "인문계열"),
    SOCIAL_SCIENCES("0011002", "사회계열"),
    BUSINESS("0011003", "상경계열"),
    SCIENCES("0011004", "이학계열"),
    ENGINEERING("0011005", "공학계열"),
    ARTS_SPORTS("0011006", "예체능계열"),
    AGRICULTURE("0011007", "농산업계열"),
    ETC("0011008", "기타"),
    NO_LIMIT("0011009", "제한없음");

    private final String code;
    private final String description;

    PlcyMajorCdEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        for (PlcyMajorCdEnum value : values()) {
            if (value.code.equals(code)) {
                return value.description;
            }
        }
        return "알 수 없음";
    }
}