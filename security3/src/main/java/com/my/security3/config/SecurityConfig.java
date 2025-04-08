package com.my.security3.config;

import com.my.security3.oauth2.CustomClientRegistrationRepo;
import com.my.security3.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepo customClientRegistrationRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        // 우리가 직접 로그인 구현 x => 외부(구글,네이버 )에게 위임
        http
                .formLogin(AbstractHttpConfigurer::disable);
        // formLogin disable 하게 되면 자동적으로 popup 로그인 창 나옴 => 해제
        http
                .httpBasic(AbstractHttpConfigurer::disable);
        // 디폴트 outh2Login 창
//        http
//                .oauth2Login(Customizer.withDefaults());

        // 우리가 직접 만든 cusomOAuth2UserService 등록
        http
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login")
                        .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                        .userInfoEndpoint((userInfoEndpointConfig ->
                                    userInfoEndpointConfig.userService(customOAuth2UserService)
                                ))
                );
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/" , "/oauth2/**" ,"/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
