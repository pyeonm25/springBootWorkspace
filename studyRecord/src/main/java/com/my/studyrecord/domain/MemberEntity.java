package com.my.studyrecord.domain;


import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Entity

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // jpa 만 내 객체를 생성할 수 있게
@Table(name="members")
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    @Column( updatable = false , unique = true )
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public MemberEntity(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = Role.STUDENT;
    }
}