package com.ongi.ongi_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.request.needHelper.PatchHelperPostRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperCommentRequestDto;
import com.ongi.ongi_back.common.dto.request.needHelper.PostHelperRequestDto;
import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperCommentsResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperIsAppliedResponse;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperLikedResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostResponseDto;
import com.ongi.ongi_back.common.entity.ChatEntity;
import com.ongi.ongi_back.common.entity.HelperApplyEntity;
import com.ongi.ongi_back.common.entity.HelperLikedEntity;
import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;
import com.ongi.ongi_back.repository.ChatRepository;
import com.ongi.ongi_back.repository.HelperApplyRepository;
import com.ongi.ongi_back.repository.HelperCommentRepository;
import com.ongi.ongi_back.repository.HelperLikedRepository;
import com.ongi.ongi_back.repository.HelperPostRepository;
import com.ongi.ongi_back.repository.UserRepository;
import com.ongi.ongi_back.service.NeedHelperService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NeedHelperServiceImplement implements NeedHelperService{
    
    private final HelperPostRepository helperPostRepository;
    private final UserRepository userRepository;
    private final HelperCommentRepository helperCommentRepository;
    private final HelperLikedRepository likedRepository;
    private final HelperApplyRepository helperApplyRepository;
    private final ChatRepository chatRepository;

    @Override
    public ResponseEntity<ResponseDto> postHelper(PostHelperRequestDto dto, String userId) {

        try {
            NeedHelperEntity needHelperEntity = new NeedHelperEntity(dto, userId);
            helperPostRepository.save(needHelperEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<? super GetHelperPostListResponseDto> getHelperPostList() {
        try {
            List<GetHelperPostResponseDto> posts = helperPostRepository.findAllWithNickname();
            return ResponseEntity.ok(new GetHelperPostListResponseDto(posts));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetHelperPostResponseDto> getHelperPost(Integer sequence) {
        try {
            GetHelperPostResponseDto post = helperPostRepository.findBySequenceWithNickname(sequence);
            if (post == null) return ResponseDto.noExistPost();
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseDto.databaseError();
        }
    }

    // 게시글 수정
    @Override
    public ResponseEntity<ResponseDto> patchHelperPost(PatchHelperPostRequestDto dto, Integer sequence, String userId) {

        try {

            NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(sequence);
            if (needHelperEntity == null) return ResponseDto.noExistPost();

            String writerId = needHelperEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            needHelperEntity.patch(dto);
            helperPostRepository.save(needHelperEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    // 게시글 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteHelperPost(Integer sequence, String userId) {

        try {

            NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(sequence);
            if (needHelperEntity == null) return ResponseDto.noExistPost();

            String writerId = needHelperEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            helperCommentRepository.deleteByPostSequence(sequence);
            helperPostRepository.delete(needHelperEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostHelperCommentRequestDto dto, Integer postSequence,
            String userId) {
        String nickname = null;
        String profileImage = null;
        
        try {
            nickname = userRepository.findByUserId(userId).getNickname();
            profileImage = userRepository.findByUserId(userId).getProfileImage();
            
            boolean existPost = helperPostRepository.existsBySequence(postSequence);
            if (!existPost) return ResponseDto.noExistPost();

            NeedHelperCommentEntity needHelperCommentEntity = new NeedHelperCommentEntity(dto, postSequence, userId, nickname, profileImage);
            helperCommentRepository.save(needHelperCommentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteHelperComment(Integer postSequence, Integer commentSequence, String userId) {
        try {

            NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(postSequence);
            if (needHelperEntity == null) return ResponseDto.noExistPost();

            NeedHelperCommentEntity needHelperCommentEntity = helperCommentRepository.findByCommentSequence(commentSequence);
            if (needHelperCommentEntity == null) return ResponseDto.noExsitComment();

            String writerId = needHelperCommentEntity.getUserId();
            boolean isWriter = writerId.equals(userId);
            if (!isWriter) return ResponseDto.noPermission();

            helperCommentRepository.deleteByCommentSequence(commentSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetHelperCommentsResponseDto> getHelperComments(Integer postSequence) {
        List<NeedHelperCommentEntity> helperCommentEntities = new ArrayList<>();
        
        try {

            helperCommentEntities = helperCommentRepository.findByPostSequence(postSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetHelperCommentsResponseDto.success(helperCommentEntities);
    }

    @Override
    public ResponseEntity<? super GetHelperCommentResponseDto> getHelperComment(Integer postSequence, Integer commentSequence) {
        NeedHelperCommentEntity needHelperCommentEntity = null;

        try {

            NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(postSequence);
            if (needHelperEntity == null) return ResponseDto.noExistPost();

            needHelperCommentEntity = helperCommentRepository.findByCommentSequence(commentSequence);
            if (needHelperCommentEntity == null) return ResponseDto.noExsitComment();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetHelperCommentResponseDto.success(needHelperCommentEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> putHelperLiked(Integer postSequence, String userId) {
        try {

            NeedHelperEntity needHelperEntity = helperPostRepository.findBySequence(postSequence);
            if (needHelperEntity == null) return ResponseDto.noExistPost();

            HelperLikedEntity likedEntity = likedRepository.findByUserIdAndLikedPostSequence(userId, postSequence);
            if (likedEntity == null) {
                likedEntity = new HelperLikedEntity(userId, postSequence);
                likedRepository.save(likedEntity);

                Integer liked_count = needHelperEntity.getLiked();
                needHelperEntity.setLiked(liked_count + 1);
                helperPostRepository.save(needHelperEntity);
            }
            else {
                likedRepository.delete(likedEntity);

                Integer liked_count = needHelperEntity.getLiked();
                needHelperEntity.setLiked(liked_count - 1);
                helperPostRepository.save(needHelperEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<? super GetHelperLikedResponseDto> getHelperLiked(Integer postSequence) {

        List<HelperLikedEntity> likedEntities = new ArrayList<>();
        
        try {

            likedEntities = likedRepository.findByLikedPostSequence(postSequence);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetHelperLikedResponseDto.success(likedEntities);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> postHelperApply(Integer postSequence, String applicantId) {
        try {      
            NeedHelperEntity post = helperPostRepository.findById(postSequence).orElseThrow();
            String requesterId = post.getUserId();      
            if(helperApplyRepository.existsByPostSequenceAndApplicantId(postSequence, applicantId)) return ResponseDto.existUser();
            ChatEntity chat = new ChatEntity();
            chat.setApplicantId(applicantId);       
            chat.setRequesterId(requesterId);      
            chat.setNeedHelperSequence(postSequence);
            chat.setChatAvailable(false);
            chatRepository.save(chat);
            chatRepository.flush(); // 강제 insert
            log.info(">> ChatEntity Saved, id: {}", chat.getChatSequence());

            HelperApplyEntity apply = new HelperApplyEntity(postSequence, post, requesterId, applicantId, chat.getChatSequence());
            apply.setIsApplied(true);
            helperApplyRepository.save(apply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.CREATED);
    }


    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteHelperApply(Integer postSequence, String applicantId) {
        try {
            boolean exists = helperApplyRepository.existsByPostSequenceAndApplicantId(postSequence, applicantId);
            if (!exists) return ResponseDto.noExistNeedhelperPost();
    
            helperApplyRepository.deleteByPostSequenceAndApplicantId(postSequence, applicantId);
            chatRepository.deleteByNeedHelperSequenceAndApplicantId(postSequence, applicantId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> getIsApplied(Integer postSequence, String applicantId) {
        try {
            HelperApplyEntity apply = helperApplyRepository.findByPostSequenceAndApplicantId(postSequence, applicantId);
            if (apply != null) {
                return ResponseEntity.ok(new GetHelperIsAppliedResponse(apply));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> accpetApply(Integer postSequence, String applicantId, String userId) {
        HelperApplyEntity apply = null;
        try {
            apply = helperApplyRepository.findByPostSequenceAndApplicantId(postSequence, applicantId);
            if(apply == null){
                return ResponseDto.noExistChatRoom();
            }
            apply.accpetApply(apply);
            helperApplyRepository.save(apply);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success(HttpStatus.OK);
    }
    
}
