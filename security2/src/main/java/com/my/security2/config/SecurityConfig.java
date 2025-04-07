package com.my.security2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //패스워드 암호화 클래스 객체
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);  // csrf 비활성화
       // 시큐리티 세션 로그인 진행 x => form login 비활성화
        http
                .formLogin((auth) -> auth.disable());
        // 시큐리티 세션 로그인 진행 x => httpBasic 비활성화
        http
                .httpBasic((auth) -> auth.disable());


        http
                .authorizeHttpRequests((auth)-> auth
                                .requestMatchers("/","/join","/login").permitAll() // 모든 권한사용자 이용가능 URL
                                .requestMatchers("/admin/**").hasRole("ADMIN") // admin 권한 사용자만 이용가능 url
                                .requestMatchers("/myPage/**").hasAnyRole("ADMIN","USER") // admin , user 일때만 이 myPage 및 하위페이지 접근가능
                                .anyRequest().authenticated()); // 다른 모든 url 다 권한 검증 거처야함




        // 세션 stateful => sataless   변경 :  jwt 토큰 staless한 세션으로 관리하겠다
        http    .sessionManagement((session)
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
