package com.my.studyrecordsecure.config.auth;

import com.my.studyrecordsecure.domain.MemberEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class CustomUserDetails implements UserDetails , OAuth2User {
    private MemberEntity memberEntity;
    private Map<String, Object> attributes;

    // 일반로그인
    public CustomUserDetails(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }
    //OAuth2.0 로그인사용
    public CustomUserDetails(MemberEntity memberEntity,  Map<String, Object> attributes) {
        this.attributes = attributes; // 구글 로그인할때 프로필 정보 이메일이 넘겨옴
        this.memberEntity = memberEntity;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // 우리 member 객체 넘겨준다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return memberEntity.getRole().toString();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getLoginId();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "";
    }
}
