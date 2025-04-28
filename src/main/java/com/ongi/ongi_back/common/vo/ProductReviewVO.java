package com.ongi.ongi_back.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.ongi.ongi_back.common.entity.ProductReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductReviewVO {
  private Integer productSequence;
  private String userId;
  private String postDate;
  private Integer rating;
  private String content;

  public ProductReviewVO(ProductReviewEntity productReviewEntity) {
    this.productSequence = productReviewEntity.getProductSequence();
    this.userId = productReviewEntity.getUserId();
    this.postDate = productReviewEntity.getPostDate();
    this.rating = productReviewEntity.getRating();
    this.content = productReviewEntity.getContent();
  }

  public static List<ProductReviewVO> getList(List<ProductReviewEntity> productReviewEntities){
    List<ProductReviewVO> list = new ArrayList<>();
    for(ProductReviewEntity productReviewEntity: productReviewEntities) {
      ProductReviewVO productReviewVO = new ProductReviewVO(productReviewEntity);
      list.add(productReviewVO);
    }

    return list;
  }
}
