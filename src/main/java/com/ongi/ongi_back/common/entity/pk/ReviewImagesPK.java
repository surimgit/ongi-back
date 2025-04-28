package com.ongi.ongi_back.common.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImagesPK implements Serializable {
  @Column(name = "review_sequence")
  private Integer reviewSequence;
  @Column(name = "image_number")
  private Integer imageNumber;
}
