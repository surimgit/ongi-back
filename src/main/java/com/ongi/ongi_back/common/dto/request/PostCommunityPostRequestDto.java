package com.ongi.ongi_back.common.dto.request;

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
public class PostCommunityPostRequestDto {
    @NotBlank
    @Pattern(regexp="^자취꿀팁|핫딜|지원사업|우리동네$")
    private String category;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
