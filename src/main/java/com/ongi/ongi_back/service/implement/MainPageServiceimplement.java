package com.ongi.ongi_back.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.dto.response.main.UserRankDto;
import com.ongi.ongi_back.common.dto.response.needHelper.GetHelperPostListResponseDto;
import com.ongi.ongi_back.common.entity.NeedHelperEntity;
import com.ongi.ongi_back.repository.HelperPostRepository;
import com.ongi.ongi_back.repository.LikeKeywordRepository;
import com.ongi.ongi_back.repository.UserRankRepository;
import com.ongi.ongi_back.service.MainPageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainPageServiceimplement implements MainPageService{

    private final LikeKeywordRepository likeKeywordRepository;
    private final HelperPostRepository helperPostRepository;
    private final UserRankRepository userRankRepository;
    
    @Override
    public ResponseEntity<GetHelperPostListResponseDto> comparisonTag(String userId) {
        List<String> userTags = likeKeywordRepository.findKeywordByUserId(userId);
        List<NeedHelperEntity> allPosts = helperPostRepository.findAll();

        List<NeedHelperEntity> sorted = allPosts.stream()
            .filter(NeedHelperEntity::isScheduleAfterNow)
            .map(post -> {
                long matchCount = post.getTagsAsList().stream()
                    .filter(userTags::contains)
                    .count();
                post.setMatchScore((int) matchCount);
                return post;
            })
            .sorted((p1, p2) -> Integer.compare(p2.getMatchScore(), p1.getMatchScore()))
            .limit(5)
            .collect(Collectors.toList());

        return ResponseEntity.ok(GetHelperPostListResponseDto.fromEntities(sorted));
    }

    @Override
    public ResponseEntity<? super UserRankDto> getCommunityUserRanking() {
        try {
            List<Object[]> rawList = userRankRepository.findCommunityUserActivityRankRaw();

            List<UserRankDto> result = rawList.stream()
                    .map(row -> new UserRankDto(
                            row[0].toString(), 
                            row[1].toString(),
                            ((Number) row[2]).intValue()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super UserRankDto> getHelperUserRanking() {
        try {
            List<Object[]> rawList = userRankRepository.findHelperUserActivityRankRaw();

            List<UserRankDto> result = rawList.stream()
                    .map(row -> new UserRankDto(
                            row[0].toString(), 
                            row[1].toString(),
                            ((Number) row[2]).intValue()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
}
