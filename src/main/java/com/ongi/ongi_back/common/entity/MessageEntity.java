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

@Entity(name="message")
@Table(name="message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

  private Integer chatSequence;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer messageSequence;

  private String content;

  private String chatDate;

  private String fileUrl;

  private Boolean isHelper;
}
