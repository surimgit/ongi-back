package com.ongi.ongi_back.common.dto.response.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ongi.ongi_back.common.dto.response.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetReservationResponseDto extends ResponseDto {
  private Integer quantity;

  public GetReservationResponseDto(Integer quantity){
    this.quantity = quantity;
  }

    public static ResponseEntity<GetReservationResponseDto> success(Integer quantity){
      GetReservationResponseDto body = new GetReservationResponseDto(quantity);

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
