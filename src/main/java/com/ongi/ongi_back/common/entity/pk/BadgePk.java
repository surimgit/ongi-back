package com.ongi.ongi_back.common.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadgePk implements Serializable{
  @Column(name="user_id")
  private String userId;

  @Column(name="badge")
  private String badge;
}
