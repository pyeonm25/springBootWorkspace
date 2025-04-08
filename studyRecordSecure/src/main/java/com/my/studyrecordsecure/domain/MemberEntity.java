package com.my.studyrecordsecure.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.my.studyrecordsecure.domain.MemberEntity.Role.ROLE_ADMIN;
import static com.my.studyrecordsecure.domain.MemberEntity.Role.ROLE_STUDENT;

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
    @Column(nullable = false)
    private String email;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyRecordEntity> studyRecords;


    @Builder
    public MemberEntity( String loginId, String username, String password, String email, String role,String provider, String name ,String providerId) {
        if(loginId ==null) this.loginId = username;
        else this.loginId = loginId;

        this.name = name;
        this.password = password;
        this.email = email;
        if( loginId !=null && loginId.equals("admin")) this.role = ROLE_ADMIN;
        else if(role == null) this.role = ROLE_STUDENT;
        else this.role = Role.valueOf(role);

        if(provider != null) this.provider = provider;
        if(providerId != null)  this.providerId = providerId;
    }

    public enum Role {
        ROLE_ADMIN, ROLE_STUDENT
    }

    //OAuth 를 위해 추가하는 필드
    private String provider;
    private String providerId;

}