package com.ongi.ongi_back.service.implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.community.PatchCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommunityRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityLikedResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.repository.CommunityCommentRepository;
import com.ongi.ongi_back.repository.CommunityPostRepository;
import com.ongi.ongi_back.repository.LikedRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImplement implements CommunityService {

    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final LikedRepository likedRepository;

    // 게시글 작성
    @Override
    public ResponseEntity<ResponseDto> postCommunityPost(PostCommunityRequestDto dto, String userId) {

        String nickname = null;
        
        try {
            nickname = userRepository.findByUserId(userId).getNickname();
            CommunityPostEntity communityPostEntity = new CommunityPostEntity(dto, userId, nickname);
            communityPostRepository.save(communityPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();

            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    // 게시글 수정
    @Override
    public ResponseEntity<ResponseDto> patchCommunityPost(PatchCommunityPostRequestDto dto, Integer postSequence, String userId) {

        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            String writerId = communityPostEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityPostEntity.patch(dto);
            communityPostRepository.save(communityPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> patchCommunityViewCount(Integer postSequence) {
        
        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            Integer viewCount = communityPostEntity.getViewCount();
            communityPostEntity.setViewCount(viewCount + 1);
            communityPostRepository.save(communityPostEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCommunityPost(Integer postSequence, String userId) {

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
    public ResponseEntity<? super GetCommunityResponseDto> getBoard(String board) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
        
        try {

            if (board.equals("인기 게시판")){
                communityPostEntities = communityPostRepository.findByHotPostTrueOrderByPostSequenceDesc();
            }
            else {
                communityPostEntities = communityPostRepository.findByBoardOrderByPostSequenceDesc(board);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCategory(String category) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
        
        try {

            communityPostEntities = communityPostRepository.findByCategoryOrderByPostSequenceDesc(category);
            
        } catch (Exception exception) {
           exception.printStackTrace();
           return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByWriter(String keyword) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();

        try {

            communityPostEntities = communityPostRepository.findByNicknameOrderByPostSequenceDesc(keyword);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByTitle(String keyword) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
        
        try {

            communityPostEntities = communityPostRepository.findByTitleContainingOrderByPostSequenceDesc(keyword);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }

    @Override
    public ResponseEntity<? super GetCommunityResponseDto> getCommunityPostByContent(String keyword) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
        
        try {

            communityPostEntities = communityPostRepository.findByContentContainingOrderByPostSequenceDesc(keyword);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityResponseDto.success(communityPostEntities);
    }  

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer postSequence, String userId) {

        String nickname = null;
        String profileImage = null;
        
        try {
            nickname = userRepository.findByUserId(userId).getNickname();
            profileImage = userRepository.findByUserId(userId).getProfileImage();
            
            boolean existPost = communityPostRepository.existsByPostSequence(postSequence);
            if (!existPost) return ResponseDto.noExistPost();

            CommunityCommentEntity communityCommentEntity = new CommunityCommentEntity(dto, postSequence, userId, nickname, profileImage);
            communityCommentRepository.save(communityCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCommunityComment(Integer postSequence, Integer commentSequence, String userId) {

        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            CommunityCommentEntity communityCommentEntity = communityCommentRepository.findByCommentSequence(commentSequence);
            if (communityCommentEntity == null) return ResponseDto.noExsitComment();

            String writerId = communityCommentEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityCommentRepository.deleteByCommentSequence(commentSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetCommunityCommentResponseDto> getCommunityComment(Integer postSequence) {

        List<CommunityCommentEntity> communityCommentEntities = new ArrayList<>();
        
        try {

            communityCommentEntities = communityCommentRepository.findByPostSequence(postSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityCommentResponseDto.success(communityCommentEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> putCommunityLiked(Integer postSequence, String userId) {
        
        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            LikedEntity likedEntity = likedRepository.findByUserIdAndLikedPostSequence(userId, postSequence);
            if (likedEntity == null) {
                likedEntity = new LikedEntity(userId, postSequence);
                likedRepository.save(likedEntity);

                Integer liked_count = communityPostEntity.getLiked();
                communityPostEntity.setLiked(liked_count + 1);
                if (communityPostEntity.getLiked() >= 10) {
                    communityPostEntity.setHotPost(true);
                }
                communityPostRepository.save(communityPostEntity);
            }
            else {
                return ResponseDto.alreadyLikedPost();
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetCommunityLikedResponseDto> getCommunityLiked(Integer postSequence) {

        List<LikedEntity> likedEntities = new ArrayList<>();
        
        try {

            likedEntities = likedRepository.findByLikedPostSequence(postSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityLikedResponseDto.success(likedEntities);
    }

    

    

}
