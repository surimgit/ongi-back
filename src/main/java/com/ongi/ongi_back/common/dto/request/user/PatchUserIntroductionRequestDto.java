package com.ongi.ongi_back.common.dto.request.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserIntroductionRequestDto {
  private String nickname;
  private String birth;

  @Pattern
  (regexp="^남|여$")
  private String gender;

  @Pattern
  (regexp="^ISTJ|ISFJ|INFJ|INTJ|ISTP|ISFP|INFP|INTP|ESTP|ESFP|ENFP|ENTP|ESTJ|ESFJ|ENFJ|ENTJ$")
  private String mbti;
  
  private String job;
  private String selfIntro;

}
                                                                              