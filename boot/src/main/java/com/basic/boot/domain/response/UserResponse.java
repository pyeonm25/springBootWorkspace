package com.basic.boot.domain.response;

import com.basic.boot.domain.UserEntity;
import lombok.Getter;

@Getter
public class UserResponse {
    private final String username;
    private final String email;

    public UserResponse(UserEntity user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
