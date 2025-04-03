package com.my.studyrecord.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // jpa 만 내 객체를 생성할 수 있게
@ToString(exclude = "studyRecords")
@Table(name="members")
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    @Column( nullable = false,updatable = false , unique = true )
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyRecordEntity> studyRecords;

    @Builder
    public MemberEntity(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = Role.STUDENT;
    }

    public enum Role {
        ADMIN, STUDENT
    }
}