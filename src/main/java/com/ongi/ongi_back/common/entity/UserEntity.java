package com.ongi.ongi_back.common.entity;

import com.ongi.ongi_back.common.dto.request.auth.SignUpRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserAddressRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserIntroductionRequestDto;
import com.ongi.ongi_back.common.dto.request.user.PatchUserPasswordRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    
    @Id
    private String userId;
    private String nickname;
    private String userPassword;
    private String address;
    private String detailAddress;
    private Integer zipCode;
    private String birth;
    private String telNumber;
    private Boolean isSeller;
    private String gender;
    private String profileImage;
    private String mbti;
    private String job;
    private String selfIntro;
    private Integer userPoint;
    private Boolean isAdmin;
    private Boolean isResigned;

    public UserEntity(SignUpRequestDto dto){
        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.nickname = dto.getNickname();
        this.telNumber = dto.getTelNumber();
        this.gender = dto.getGender();

        this.userPoint = 0;
        this.isAdmin = false;
        this.isResigned = false;
    }

    public void patchIntroduction(PatchUserIntroductionRequestDto dto){
        if (dto.getNickname() != null) this.nickname = dto.getNickname();
        if (dto.getBirth() != null) this.birth = dto.getBirth();
        if (dto.getGender() != null) this.gender = dto.getGender();
        if (dto.getMbti() != null) this.mbti = dto.getMbti();
        if (dto.getJob() != null) this.job = dto.getJob();
        if (dto.getSelfIntro() != null) this.selfIntro = dto.getSelfIntro();
        if (dto.getProfileImage() != null) this.profileImage = dto.getProfileImage();
    }

    public void patchUserPassword(PatchUserPasswordRequestDto dto){
        if (dto.getNewPassword() != null) this.userPassword = dto.getNewPassword();
    }

    public void patchUserAddress(PatchUserAddressRequestDto dto){
        this.address = dto.getAddress();
        this.detailAddress = dto.getDetailAddress();
    }

}
