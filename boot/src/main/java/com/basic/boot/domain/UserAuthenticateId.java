package com.basic.boot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserAuthenticateId { // 복합키 클래스
    private String username;
    private String role;
}
