package com.my.security2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinLoginRequest {
    private String username;
    private String password;
}
