package com.my.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 스프링부트 시작할때 해당 구성설정값을 가지고 컨테이너를 생성해라 : 환경설정파일 암시
@EnableWebSecurity // 스프링부트 시큐리티 프레임워크가 관리하는 환경설정파일
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
        http
                .authorizeHttpRequests((auth)-> auth
                                .requestMatchers("/","/main","/join","joinProc","/login","/loginProc").permitAll() // 모든 권한사용자 이용가능 URL
                                .requestMatchers("/admin/**").hasRole("ADMIN") // admin 권한 사용자만 이용가능 url
                                .requestMatchers("/myPage/**").hasAnyRole("ADMIN","USER") // admin , user 일때만 이 myPage 및 하위페이지 접근가능
                                .anyRequest().authenticated()); // 다른 모든 url 다 권한 검증 거처야함

        http
                .formLogin((auth) -> auth
                        .loginPage("/login") // 우리가 만든 loginPage
                        .loginProcessingUrl("/loginProc") // form post => url 명시한거 들어감
                        .permitAll()
                );

        return http.build();
    }
}
