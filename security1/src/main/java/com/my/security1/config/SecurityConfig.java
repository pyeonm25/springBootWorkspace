package com.my.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 스프링부트 시작할때 해당 구성설정값을 가지고 컨테이너를 생성해라 : 환경설정파일 암시
@EnableWebSecurity // 스프링부트 시큐리티 프레임워크가 관리하는 환경설정파일
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);  // csrf 비활성화
        http
                .authorizeHttpRequests((auth)-> auth
                                .requestMatchers("/").permitAll() // 모든 권한사용자 이용가능 URL
                                .requestMatchers("/admin").hasRole("ADMIN") // admin 권한 사용자만 이용가능 url
                                .anyRequest().authenticated()); // 다른 모든 url 다 권한 검증 거처야함
        return http.build();
    }
}
