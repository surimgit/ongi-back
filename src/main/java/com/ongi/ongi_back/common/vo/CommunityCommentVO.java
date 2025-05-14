package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.CommunityCommentEntity;

import lombok.Getter;

@Getter
public class CommunityCommentVO {
    private Integer postSequence;
    private Integer commentSequence;
    private String commentWriterId;
    private String nickname;
    private String commentPostDate;
    private String comment;

    private CommunityCommentVO(CommunityCommentEntity communityCommentEntity) {
        this.postSequence = communityCommentEntity.getPostSequence();
        this.commentSequence = communityCommentEntity.getCommentSequence();
        this.commentWriterId = communityCommentEntity.getUserId();
        this.nickname = communityCommentEntity.getNickname();
        this.commentPostDate = communityCommentEntity.getPostDate();
        this.comment = communityCommentEntity.getContent();
    }

    public static List<CommunityCommentVO> getList(List<CommunityCommentEntity> communityCommentEntities) {
        List<CommunityCommentVO> list = new ArrayList<>();

        for (CommunityCommentEntity communityCommentEntity: communityCommentEntities) {
            CommunityCommentVO vo = new CommunityCommentVO(communityCommentEntity);
            list.add(vo);
        }

        return list;
    }
}
