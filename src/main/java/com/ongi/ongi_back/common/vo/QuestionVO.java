package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.QuestionEntity;

import lombok.Getter;

@Getter
public class QuestionVO {
  private Integer questionSequence;
  private String postDate;
  private String category;
  private String title;
  private boolean isAnswered;

  private QuestionVO(QuestionEntity questionEntity){
    this.questionSequence = questionEntity.getQuestionSequence();
    this.postDate = questionEntity.getPostDate();
    this.category = questionEntity.getCategory();
    this.title = questionEntity.getTitle();
    this.isAnswered = questionEntity.isAnswered();
  }

  public static List<QuestionVO> getList(List<QuestionEntity> questionEntities){
    List<QuestionVO> list = new ArrayList<>();
    for(QuestionEntity questionEntity : questionEntities){
      QuestionVO vo = new QuestionVO(questionEntity);
      list.add(vo);
    }
    return list;
  }

}
