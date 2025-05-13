package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ongi.ongi_back.common.dto.request.community.PatchCommunityCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PatchCommunityPostRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.community.PostCommunityRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityLikedResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityPostResponseDto;
import com.ongi.ongi_back.common.dto.response.community.GetCommunityResponseDto;
import com.ongi.ongi_back.common.entity.CommunityCommentEntity;
import com.ongi.ongi_back.common.entity.CommunityPostEntity;
import com.ongi.ongi_back.common.entity.LikedEntity;
import com.ongi.ongi_back.common.entity.UserEntity;
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

    // 업로드 경로
    @Value("${file.path}")
    private String uploadDirectory;
    @Value("${file.url}")
    private String fileUrl;

    // 게시글 작성
    @Override
    public ResponseEntity<ResponseDto> postCommunityPost(PostCommunityRequestDto dto, String userId) {

        UserEntity userEntity = null;
        String nickname = null;
        String county = null;
        
        try {
            userEntity = userRepository.findByUserId(userId);
            nickname = userEntity.getNickname();

            if (dto.getBoard().equals("우리 동네 게시판")) {
                county = userEntity.getAddress();

                String[] parts = county.split(" ");
                county = (parts.length >= 2) ? parts[0] + " " + parts[1] : county;
                if (parts[0].equals("세종특별자치시")) county = "세종특별자치시";
            }

            CommunityPostEntity communityPostEntity = new CommunityPostEntity(dto, userId, nickname, county);
            communityPostRepository.save(communityPostEntity);

            Integer point = userEntity.getUserPoint();
            userEntity.setUserPoint(point + 3);
            userRepository.save(userEntity);
            
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

            UserEntity userEntity = userRepository.findByUserId(userId);

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            String writerId = communityPostEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityCommentRepository.deleteByPostSequence(postSequence);
            communityPostRepository.delete(communityPostEntity);

            if (userEntity.getIsResigned()) {}
            else {
                Integer point = userEntity.getUserPoint();
                userEntity.setUserPoint(point - 3);
                userRepository.save(userEntity);
            }
            
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
    public ResponseEntity<? super GetCommunityResponseDto> getCounty(String county, String category) {

        List<CommunityPostEntity> communityPostEntities = new ArrayList<>();
        
        try {
            if (category == null) communityPostEntities = communityPostRepository.findCountyPosts(county);
            else communityPostEntities = communityPostRepository.findCountyCategoryPosts(county, category);
            
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
        
        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            String nickname = userEntity.getNickname();
            
            boolean existPost = communityPostRepository.existsByPostSequence(postSequence);
            if (!existPost) return ResponseDto.noExistPost();

            CommunityCommentEntity communityCommentEntity = new CommunityCommentEntity(dto, postSequence, userId, nickname);
            communityCommentRepository.save(communityCommentEntity);

            Integer point = userEntity.getUserPoint();
            userEntity.setUserPoint(point + 1);
            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCommunityComment(Integer postSequence, Integer commentSequence, String userId) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            CommunityCommentEntity communityCommentEntity = communityCommentRepository.findByCommentSequence(commentSequence);
            if (communityCommentEntity == null) return ResponseDto.noExsitComment();

            String writerId = communityCommentEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityCommentRepository.deleteByCommentSequence(commentSequence);

            if (userEntity.getIsResigned()) {}
            else {
                Integer point = userEntity.getUserPoint();
                userEntity.setUserPoint(point - 1);
                userRepository.save(userEntity);
            }
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetCommunityCommentsResponseDto> getCommunityComments(Integer postSequence) {

        List<CommunityCommentEntity> communityCommentEntities = new ArrayList<>();
        
        try {

            communityCommentEntities = communityCommentRepository.findByPostSequence(postSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityCommentsResponseDto.success(communityCommentEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> patchCommunityComment(PatchCommunityCommentRequestDto dto, Integer postSequence, Integer commentSequence, String userId) {
        
        CommunityCommentEntity communityCommentEntity = null;

        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            communityCommentEntity = communityCommentRepository.findByCommentSequence(commentSequence);
            if (communityCommentEntity == null) return ResponseDto.noExsitComment();

            String writerId = communityCommentEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            communityCommentEntity.setContent(dto.getContent());
            communityCommentRepository.save(communityCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetCommunityCommentResponseDto> getCommunityComment(Integer postSequence, Integer commentSequence) {
        
        CommunityCommentEntity communityCommentEntity = null;

        try {

            CommunityPostEntity communityPostEntity = communityPostRepository.findByPostSequence(postSequence);
            if (communityPostEntity == null) return ResponseDto.noExistPost();

            communityCommentEntity = communityCommentRepository.findByCommentSequence(commentSequence);
            if (communityCommentEntity == null) return ResponseDto.noExsitComment();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommunityCommentResponseDto.success(communityCommentEntity);
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

    @Override
    @Scheduled(cron = " 0 0 12 * * ? ")
    @Transactional
    public void selectHotPosts() {

        List<CommunityPostEntity> hotPosts = new ArrayList<>();
        hotPosts = communityPostRepository.findTop10RecentPopularPosts();

        for (CommunityPostEntity communityPostEntity: hotPosts) {
            communityPostEntity.setHotPost(true);
        }
        communityPostRepository.saveAll(hotPosts);
    }

}
