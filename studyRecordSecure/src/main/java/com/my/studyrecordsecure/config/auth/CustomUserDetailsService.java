package com.my.studyrecordsecure.config.auth;

import com.my.studyrecordsecure.domain.MemberEntity;
import com.my.studyrecordsecure.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepositroy memberRepositroy;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.info("loadUserByUsername = {}" , loginId +" 멤버객제 만들었다");
        MemberEntity member = memberRepositroy.findByLoginId(loginId);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().toString()));

        if(member != null){
            // 커스텀 유저 디테일 클래스를 만들어서 우리 멤버 객체 주입
            return new CustomUserDetails(member);
        }
        return null;
    }
}
