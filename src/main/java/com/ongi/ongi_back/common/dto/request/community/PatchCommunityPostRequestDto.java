package com.ongi.ongi_back.common.dto.request.community;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchCommunityPostRequestDto {
    @NotBlank
    String title;
    @NotBlank
    String content;
    @NotBlank
    @Pattern(regexp="^공부|미용|여행|영화/드라마|운동|자취꿀팁|재테크|패션|핫딜$")
    String category;
}
