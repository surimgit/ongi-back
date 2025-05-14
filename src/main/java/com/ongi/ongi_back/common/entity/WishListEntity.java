package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.entity.pk.WishListPk;

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

@Entity(name = "wish_list")
@Table(name = "wish_list")
@IdClass(WishListPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListEntity {
  @Id
  private String userId;
  @Id
  private Integer productSequence;

  @Override
  public String toString() {
      return "WishListEntity{" +
            "userId='" + userId + '\'' +
            ", productSequence=" + productSequence +
            '}';
  }

}
