package com.my.studyrecordsecure.config;

import com.my.studyrecordsecure.config.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // 우리의 필터를 실행
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomAuthFailureHandler customAuthFailureHandler;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() { // 스프링 시큐리티 기능 비활성화
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/static/**"),
                        new AntPathRequestMatcher("/img/**"),
                        new AntPathRequestMatcher("/css/**"),
                        new AntPathRequestMatcher("/js/**")
                );
    }

    // csrf 해제전

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // 개발할때만

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/test","/login" , "/" , "/sign-up","/member","/login-form","/auth").permitAll()
                        .requestMatchers("/member/**").hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers("/study/**").hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .formLogin(
                        form->{
                            form.loginPage("/login-form")
                                    .loginProcessingUrl("/login")// 우리가 만든 로그인페이지로 자동 인터셉트됨
                                    .failureHandler( customAuthFailureHandler)
                                    .defaultSuccessUrl("/test", true);

                        }
                ).oauth2Login(
                        oauth2 -> oauth2
                                .loginPage("/login-form")
                                .successHandler(customSuccessHandler)
                                .failureHandler(customAuthFailureHandler)
                                .permitAll()

                );

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        return http.build();
    }

    // 패스워드 암호과 객체 생성
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }









}


//csrf 해제 후
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    //     http.csrf(AbstractHttpConfigurer::disable); // 개발할때만
//
//    http
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/home", "/test","/login" , "/" , "/sign-up","/member","/login-form","/auth").permitAll()
//                    .requestMatchers("/member/**").hasAnyRole("STUDENT","ADMIN")
//                    .requestMatchers("/admin").hasRole("ADMIN")
//                    .requestMatchers("/error").permitAll()
//                    .anyRequest().authenticated()
//            );
//
//    http
//            .formLogin(
//                    form->{
//                        form.loginPage("/login-form")
//                                .loginProcessingUrl("/login")// 우리가 만든 로그인페이지로 자동 인터셉트됨
//                                .failureHandler( customAuthFailureHandler)
//                                .defaultSuccessUrl("/test", true);
//
//                    }
//            ).oauth2Login(
//                    oauth2 -> oauth2
//                            .loginPage("/login-form")
//                            .successHandler(customSuccessHandler)
//                            .failureHandler(customAuthFailureHandler)
//                            .permitAll()
//
//            );
//
//    // csrf 해제할때 적용
//    http
//            .logout((auth) -> auth.logoutUrl("/logout")
//                    .logoutSuccessUrl("/"));
//
//    http
//            .sessionManagement((auth) -> auth
//                    .maximumSessions(1)
//                    .maxSessionsPreventsLogin(true));
//
//    http
//            .sessionManagement((auth) -> auth
//                    .sessionFixation().changeSessionId());
//
//
//    return http.build();
//}