package com.ongi.ongi_back.common.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;
import com.ongi.ongi_back.common.vo.MyBuyingVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMyBuyingResponseDto extends ResponseDto {
  private List<MyBuyingVO> myBuying;

  private GetMyBuyingResponseDto(List<MyBuyingVO> myBuying){
    this.myBuying = myBuying;
  }

  public static ResponseEntity<GetMyBuyingResponseDto> success(List<MyBuyingVO> myBuying) {
    GetMyBuyingResponseDto body = new GetMyBuyingResponseDto(myBuying);
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

}
