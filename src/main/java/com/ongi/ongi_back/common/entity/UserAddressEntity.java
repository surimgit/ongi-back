package com.ongi.ongi_back.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ongi.ongi_back.common.dto.request.shoppingCart.PostUserAddressRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_address")
@Table(name = "user_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String userId;
  private String addressLabel;
  private String recipientName;
  private String phone;
  private String zipcode;
  private String address;
  private String detailAddress;
  private Boolean isDefault;
  private String createdAt;

  public UserAddressEntity(PostUserAddressRequestDto dto, String userId){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    this.userId = userId;
    this.addressLabel = dto.getAddressLabel();
    this.recipientName = dto.getRecipientName();
    this.phone = dto.getPhone();
    this.zipcode = dto.getZipcode();
    this.address = dto.getAddress();
    this.detailAddress = dto.getDetailAddress();
    this.createdAt = now.format(dateTimeFormatter);
  }
}
