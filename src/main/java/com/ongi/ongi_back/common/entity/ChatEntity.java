package com.ongi.ongi_back.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="chat")
@Table(name="chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer chatSequnece;
  private String helperId;
  private Integer needHelperSequence;
  private Boolean chatAvaiable;

  
}
