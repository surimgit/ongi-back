package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostInfoPostRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.repository.CommunityCommentRepository;
import com.ongi.ongi_back.repository.CommunityPostRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImplement implements CommunityService {

    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;
    private final CommunityCommentRepository communityCommentRepository;

    @Override
    public ResponseEntity<ResponseDto> postInfo(PostInfoPostRequestDto dto, String userId) {

        String nickname = null;
        
        try {
            nickname = userRepository.findByUserId(userId).getNickname();
            CommunityPostEntity informationPostEntity = new CommunityPostEntity(dto, userId, nickname);
            communityPostRepository.save(informationPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();

            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteInfo(Integer postSequence, String userId) {

        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            String writerId = communityPostEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityCommentRepository.deleteByPostSequence(postSequence);
            communityPostRepository.delete(communityPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetCommunityPostResponseDto> getCommunityPost(Integer postSequence) {

        CommunityPostEntity communityPostEntity = null;
        
        try {

            communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return GetCommunityPostResponseDto.success(communityPostEntity);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCommunity() {
        
        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();

        try {

            communityPostEntities = communityPostRepository.findByOrderByPostSequenceDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCommunityByWriter(String nickname) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();

        try {

            communityPostEntities = communityPostRepository.findByNickname(nickname);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer postSequence, String userId) {

        String nickname = null;
        
        try {
            nickname = userRepository.findByUserId(userId).getNickname();
            
            boolean existPost = communityPostRepository.existsByPostSequence(postSequence);
            if (!existPost) return ResponseDto.noExistPost();

            CommunityCommentEntity communityCommentEntity = new CommunityCommentEntity(dto, postSequence, userId, nickname);
            communityCommentRepository.save(communityCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    

    
    
}
