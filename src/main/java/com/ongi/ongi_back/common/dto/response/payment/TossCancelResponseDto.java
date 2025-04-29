package com.ongi.ongi_back.common.dto.response.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ongi.ongi_back.common.vo.TossCancel;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossCancelResponseDto {
  List<TossCancel> cancels;
}
