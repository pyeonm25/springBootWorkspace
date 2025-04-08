package com.my.studyrecordsecure.config;

import com.my.studyrecordsecure.config.auth.CustomUserDetails;
import com.my.studyrecordsecure.domain.MemberEntity;
import com.my.studyrecordsecure.repository.MemberRepositroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberRepositroy memberRepositroy;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)oAuth2User;
        log.info("oAuth2User = " + oAuth2User);
        log.info("userDetails = " + userDetails);
        MemberEntity member = userDetails.getMemberEntity();
        log.info("member = " + member);
        log.info("member.getProvider = " + member.getProvider());
        log.info("member.getProviderId = " + member.getProviderId());
        response.sendRedirect("/oauth");


    }

}
