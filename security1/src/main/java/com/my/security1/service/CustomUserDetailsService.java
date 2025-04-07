package com.my.security1.service;

import com.my.security1.domain.UserEntity;
import com.my.security1.dto.CustomUserDetails;
import com.my.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    // 시큐리티 인증 매니져 클래스가 해당 userDetails 클래스를 다루기 위해서 사용하는 서비스 클래스객체
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user != null){
            return new CustomUserDetails(user);
        }
        return null;
    }
}
