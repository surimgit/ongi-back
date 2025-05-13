package com.ongi.ongi_back.common.dto.request.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEventApplyRequestDto {
    private String userId;
    private Integer eventSequence;
}
