package com.ongi.ongi_back.common.dto.request.chat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveMessageRequestDto {
  @NotBlank
  private Integer chatSequence;

  @NotBlank
  private String senderId;

  @NotBlank
  private String content;

  private String fileUrl;

}
