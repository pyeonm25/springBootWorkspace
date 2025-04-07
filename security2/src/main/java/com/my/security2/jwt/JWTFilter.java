package com.my.security2.jwt;

import com.my.security2.domain.UserEntity;
import com.my.security2.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//  jwt 있는지 없는지 검사하는 필터
@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {  // 요청이 들어오면 한번만 작동하는 필터
    private final JWTUtil jwtUtil;

    // request header 암호화된 jwt 토큰 -> 복호화
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization"); // 값 Bearer~

        //Authorization 검증
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("token null");
            filterChain.doFilter(request, response); // 그 다음 필터 실행해
            return;

        }
        // "Bearer " 때고 암호 복호화 진쟁
        String token = authorization.split(" ")[1];
        // 토큰 만료 확인
        if(jwtUtil.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response); // 그 다음 필터 실행해
            return;
        }

        // 토큰값에서 암호를 복호화 해서 값을 꺼내옴
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // 시큐리티 세션(stateless ) 값을 저장 => userEntity 객체

        // userEntity 객체 생성
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setRole(role);
        userEntity.setPassword("temppassword");

        // userDetails 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails , null , customUserDetails.getAuthorities());

        // 시큐리티 컨텍스트 홀더 => 사용자 등록  (시큐리티 세션 객체 저장 )
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response); // 그 다음 필터 실행해
        return;

    }



}
