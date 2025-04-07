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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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
        log.info(" authToken = {}" , authToken.toString());
        // 시큐리티 컨텍스트 홀더 => 사용자 등록  (시큐리티 세션 객체 저장 )
        SecurityContextHolder.getContext().setAuthentication(authToken);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("name = {}" , name);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); // 콜랙션 객체  => 인덱스 없어서
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator(); // iterator 변경해야지만이 안에 있는 값 접근 가능
        GrantedAuthority grantedAuthority = iterator.next();
        String role2 = grantedAuthority.getAuthority();
        log.info("role2 = {}" , role2);
        filterChain.doFilter(request, response); // 그 다음 필터 실행해
        return;

    }



}
