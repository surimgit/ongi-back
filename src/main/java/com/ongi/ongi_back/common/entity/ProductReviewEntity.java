package com.ongi.ongi_back.common.entity;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.user.PostProductReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_review")
@Table(name = "product_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reviewSequence;
  private Integer productSequence;
  private String userId;
  private String postDate;
  private Integer rating;
  private String content;

  public ProductReviewEntity(PostProductReviewRequestDto dto, String userId){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.productSequence = dto.getProductSequence();
    this.rating = dto.getRating();
    this.content = dto.getContent();
    this.userId = userId;
    this.postDate = now.format(dateTimeFormatter);
  }
}
