package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.CommunityPostEntity;

import lombok.Getter;

@Getter
public class CommunityVO {
    private Integer postSequence;
    private String userId;
    private String nickname;
    private String postDate;
    private String category;
    private String title;
    private Integer liked;
    private Integer viewCount;

    private CommunityVO(CommunityPostEntity communityPostEntity) {
        this.postSequence = communityPostEntity.getPostSequence();
        this.userId = communityPostEntity.getUserId();
        this.nickname = communityPostEntity.getNickname();
        this.postDate = communityPostEntity.getPostDate();
        this.category = communityPostEntity.getCategory();
        this.title = communityPostEntity.getTitle();
        this.liked = communityPostEntity.getLiked();
        this.viewCount = communityPostEntity.getViewCount();
    }

    public static List<CommunityVO> getList(List<CommunityPostEntity> communityEntities) {
        List<CommunityVO> list = new ArrayList<>();
        for(CommunityPostEntity communityPostEntity: communityEntities) {
            CommunityVO vo = new CommunityVO(communityPostEntity);
            list.add(vo);
        }
        return list;
    }
}
