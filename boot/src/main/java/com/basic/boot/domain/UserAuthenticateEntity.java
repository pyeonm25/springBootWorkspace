package com.basic.boot.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

@Entity
@IdClass(UserAuthenticateId.class) // jpa -> 이 클래스를 Id클래스로 한다
public class UserAuthenticateEntity { // 인증

    // 복합키 => 한개 이상의 컬럼 pk를 가질때
    @Id
    private String username;
    @Id
    private String role;
    private Integer enable;
    @ManyToOne(fetch= FetchType.LAZY)
    private UserEntity user;  // username => pk
}
