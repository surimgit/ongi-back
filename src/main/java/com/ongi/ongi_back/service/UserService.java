package com.ongi.ongi_back.service;

import org.springframework.http.ResponseEntity;
import com.ongi.ongi_back.common.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);
}
