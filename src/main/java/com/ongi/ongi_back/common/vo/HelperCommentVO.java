package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.NeedHelperCommentEntity;

import lombok.Getter;

@Getter
public class HelperCommentVO {
    private Integer postSequence;
    private Integer commentSequence;
    private String commentWriterId;
    private String nickname;
    private String commentPostDate;
    private String comment;
    private String profileImage;

    private HelperCommentVO(NeedHelperCommentEntity helperCommentEntity) {
        this.postSequence = helperCommentEntity.getPostSequence();
        this.commentSequence = helperCommentEntity.getCommentSequence();
        this.commentWriterId = helperCommentEntity.getUserId();
        this.nickname = helperCommentEntity.getNickname();
        this.commentPostDate = helperCommentEntity.getPostDate();
        this.comment = helperCommentEntity.getContent();
        this.profileImage = helperCommentEntity.getProfileImage();
    }

    public static List<HelperCommentVO> getList(List<NeedHelperCommentEntity> helperCommentEntities) {
        List<HelperCommentVO> list = new ArrayList<>();

        for (NeedHelperCommentEntity helperCommentEntity: helperCommentEntities) {
            HelperCommentVO vo = new HelperCommentVO(helperCommentEntity);
            list.add(vo);
        }

        return list;
    }
    
}
