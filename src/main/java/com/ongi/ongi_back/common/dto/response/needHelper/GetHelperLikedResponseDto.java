package com.ongi.ongi_back.common.dto.response.needHelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.HelperLikedEntity;

import lombok.Getter;

@Getter
public class GetHelperLikedResponseDto extends ResponseDto {
    private List<String> likes;

    private GetHelperLikedResponseDto(List<HelperLikedEntity> likedEntities) {
        this.likes = new ArrayList<>();
        for (HelperLikedEntity likedEntity: likedEntities) {
            String userId = likedEntity.getUserId();
            this.likes.add(userId);
        }
    }

    public static ResponseEntity<GetHelperLikedResponseDto> success(List<HelperLikedEntity> likedEntities) {
        GetHelperLikedResponseDto body = new GetHelperLikedResponseDto(likedEntities);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
