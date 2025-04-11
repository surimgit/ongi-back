package com.ongi.ongi_back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.PostCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.repository.CommunityPostRepository;
import com.ongi.ongi_back.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImplement implements CommunityService {

    private final CommunityPostRepository communitypostRepository;

    @Override
    public ResponseEntity<ResponseDto> postInfo(PostCommunityPostRequestDto dto, String userId) {
        
        try {

            CommunityPostEntity informationPostEntity = new CommunityPostEntity(dto, userId);
            communitypostRepository.save(informationPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();

            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }
    
}
