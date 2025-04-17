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
public class PostCommunityRequestDto {
    @NotBlank
    @Pattern(regexp="^공부|미용|여행|영화/드라마|운동|자취꿀팁|재테크|패션|핫딜|정보기타|동네생활|모임|우리동네기타$")
    private String category;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
