package com.ongi.ongi_back.common.dto.request.community;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchCommunityCommentRequestDto {
    @NotBlank
    private String content;
}
