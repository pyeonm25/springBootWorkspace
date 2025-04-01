package com.basic.boot.domain.request;

import com.basic.boot.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserRequest {
    private String username;
    private String password;
    private String email;

    public UserEntity toEntity() {
       // return new UserEntity(username, password, email);
        return UserEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
