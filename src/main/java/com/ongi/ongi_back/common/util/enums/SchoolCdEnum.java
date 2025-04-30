package com.ongi.ongi_back.common.util.enums;

import lombok.Getter;

@Getter
public enum SchoolCdEnum {
    HIGH_SCHOOL_BELOW("0049001", "고졸 미만"),
    HIGH_SCHOOL_ATTENDING("0049002", "고교 재학"),
    HIGH_SCHOOL_EXPECTED("0049003", "고졸 예정"),
    HIGH_SCHOOL_GRADUATED("0049004", "고교 졸업"),
    UNIVERSITY_ATTENDING("0049005", "대학 재학"),
    UNIVERSITY_EXPECTED("0049006", "대졸 예정"),
    UNIVERSITY_GRADUATED("0049007", "대학 졸업"),
    GRADUATE_SCHOOL("0049008", "석박사"),
    ETC("0049009", "기타"),
    NO_LIMIT("0049010", "제한없음");

    private final String code;
    private final String description;

    SchoolCdEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        for (SchoolCdEnum value : values()) {
            if (value.code.equals(code)) {
                return value.description;
            }
        }
        return "알 수 없음";
    }
}