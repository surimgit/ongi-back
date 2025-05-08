package com.ongi.ongi_back.common.dto.request.community;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostImageRequestDto {
    List<MultipartFile> images;
}
