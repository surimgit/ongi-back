package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyListResponseDto;
import com.ongi.ongi_back.common.dto.response.calendar.GetPolicyViewResponseDto;
import com.ongi.ongi_back.common.entity.YouthCenterEntity;
import com.ongi.ongi_back.common.vo.YouthCenterListVO;
import com.ongi.ongi_back.service.YouthCenterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YouthCenterServiceImplement implements YouthCenterService{

    @Value("${youthcenter.apiUrl}")
    private String apiUrl;

    @Value("${youthcenter.apiKey}")
    private String apiKeyNm;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<? super GetPolicyListResponseDto> getPolicyList(String keyword) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apiKeyNm", apiKeyNm)
                .queryParam("pageNum", 1)
                .queryParam("pageSize", 1000)
                .queryParam("rtnType", "json")
                .queryParam("plcyNm", keyword)
                .build()
                .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode youthPolicyList = jsonNode.path("result").path("youthPolicyList");

            List<YouthCenterEntity> entityList = new ArrayList<>();
            for (JsonNode node : youthPolicyList) {
                YouthCenterEntity policy = objectMapper.treeToValue(node, YouthCenterEntity.class);
                entityList.add(policy);
            }

            List<YouthCenterListVO> voList = YouthCenterListVO.getList(entityList);

            return ResponseEntity.ok(voList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetPolicyViewResponseDto> getPolicyView(String plcyNm, String plcyNo) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apiKeyNm", apiKeyNm)
                .queryParam("pageNum", 1)
                .queryParam("pageSize", 10)
                .queryParam("rtnType", "json")
                .queryParam("plcyNm", plcyNm)
                .build()
                .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            JsonNode youthPolicyList = rootNode
                .path("result")
                .path("youthPolicyList");

            if (youthPolicyList.isMissingNode() || !youthPolicyList.isArray() || youthPolicyList.size() == 0) {
                return ResponseDto.noExistPost();
            }

            List<YouthCenterEntity> policies = new ArrayList<>();
            for (JsonNode node : youthPolicyList) {
                YouthCenterEntity policy = objectMapper.treeToValue(node, YouthCenterEntity.class);
                if (plcyNo.equals(policy.getPlcyNo())) {
                    policies.add(policy);
                }
            }

            return GetPolicyViewResponseDto.success(policies);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    
}
