package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PostReviewImagesRequestDto;
import com.ongi.ongi_back.common.entity.pk.ReviewImagesPK;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "review_images")
@Table(name = "review_images")
@IdClass(ReviewImagesPK.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImagesEntity {
  @Id
  private Integer reviewSequence;
  @Id
  private Integer imageNumber;
  private String reviewImage;

  public ReviewImagesEntity(PostReviewImagesRequestDto dto){
    this.reviewSequence = dto.getReviewSequence();
    this.imageNumber = dto.getImageNumber();
    this.reviewImage = dto.getReviewImage();
  }
}
