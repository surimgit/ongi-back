package com.ongi.ongi_back.common.util.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum RegionCdEnum {
    SEOUL("11", "서울"),
    BUSAN("26", "부산"),
    DAEGU("27", "대구"),
    INCHEON("28", "인천"),
    GWANGJU("29", "광주"),
    DAEJEON("30", "대전"),
    ULSAN("31", "울산"),
    SEJONG("36", "세종"),
    GYEONGGI("41", "경기"),
    CHUNGBUK("43", "충북"),
    CHUNGNAM("44", "충남"),
    JEONNAM("46", "전남"),
    GYEONGBUK("47", "경북"),
    GYEONGNAM("48", "경남"),
    JEJU("50", "제주"),
    GANGWON("51", "강원"),
    JEONBUK("52", "전북");

    private final String code;
    private final String name;

    RegionCdEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
        
    public static String getNameFromZipCd(String zipCd) {
    if (zipCd == null || zipCd.isBlank()) return "알 수 없음";

    Set<String> detectedRegionCodes = new HashSet<>();
    String[] zipCodes = zipCd.split(",");

    for (String zip : zipCodes) {
        if (zip.length() < 2) continue;
        String codePrefix = zip.substring(0, 2);

        for (RegionCdEnum region : RegionCdEnum.values()) {
                if (region.getCode().equals(codePrefix)) {
                    detectedRegionCodes.add(codePrefix);
                }
            }
        }

        Set<String> allRegionCodes = Arrays.stream(RegionCdEnum.values())
            .map(RegionCdEnum::getCode)
            .collect(Collectors.toSet());

        if (detectedRegionCodes.containsAll(allRegionCodes)) {
            return "전국";
        }

        List<String> regionNames = new ArrayList<>();
        for (RegionCdEnum region : RegionCdEnum.values()) {
            if (detectedRegionCodes.contains(region.getCode())) {
                regionNames.add(region.getName());
            }
        }

        if (regionNames.isEmpty()) {
            return "알 수 없음";
        }

        return String.join(", ", regionNames);
    }

}