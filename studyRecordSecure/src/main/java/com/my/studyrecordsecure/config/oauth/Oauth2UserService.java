package com.my.studyrecordsecure.config.oauth;

import com.my.studyrecordsecure.config.auth.CustomUserDetails;
import com.my.studyrecordsecure.config.oauth.provider.GoogleUserInfo;
import com.my.studyrecordsecure.config.oauth.provider.NaverUserInfo;
import com.my.studyrecordsecure.domain.MemberEntity;
import com.my.studyrecordsecure.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class Oauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepositroy memberRepositroy;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("user request clientRegistration : " + userRequest.getClientRegistration());
        log.info("user reuset getAccessToken :" + userRequest.getAccessToken().getTokenValue());
        OAuth2User oAuth2User = super.loadUser(userRequest); // google 회원 프로필 조회
        log.info("get Attribute : " + oAuth2User.getAttributes());
        // loadUser --> Authentication 객체 안에 들어간다
        return processOAuthUser(userRequest , oAuth2User);
    }
    private OAuth2User processOAuthUser(OAuth2UserRequest userRequest , OAuth2User oAuth2User){
        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            log.info("구글 로그인");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            log.info("네이버 로그인");
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        } else {
            log.info("요청 실패 ");
        }
        //log.info("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
        //log.info("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
        Optional<MemberEntity> userOptional =
                memberRepositroy.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
        log.info("userOptional.isPresent()  ={} " , userOptional.isPresent() );
        MemberEntity memberEntity = null;
        if (userOptional.isEmpty()) {

            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            memberEntity = MemberEntity.builder()
                    .username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
                    .name(oAuth2UserInfo.getName())
                    .email(oAuth2UserInfo.getEmail())
                    .password("tempPassword")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            memberRepositroy.save(memberEntity);
        }else{
            memberEntity = userOptional.get();
        }

        return new CustomUserDetails( memberEntity, oAuth2User.getAttributes());
    }

}
