package com.my.security2.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    // 로그인 검증 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
       // 클라이언트가 요청한 username, userpassword 추출
        String username =obtainUsername(request);
       String password =obtainPassword(request);

       // authenticationManager값 전달
        // UsernamePasswordAuthenticationToken => dto 객체
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password , null);
        log.info("attemptAuthentication authToken = : {}", authToken);

       // 우리가 담은 token authenticationManager 전달
        return authenticationManager.authenticate(authToken);
    }
    // 로그인 성공시에 실행하는 메서드 -> jwt 발행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      log.info("successful Authentication " );

    }
   // 로그인 실패시 실행하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 에러 코드 발생
        log.info("unsuccessful Authentication " );

    }
}
