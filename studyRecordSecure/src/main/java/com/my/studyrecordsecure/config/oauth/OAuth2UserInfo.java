package com.my.studyrecordsecure.config.oauth;

// 여러 OAuth2.0 제공자들이 있는데 속성값들이 다 다르다 => 공통적으로 만들어주는 작업 필요하다
public interface OAuth2UserInfo {
    //제공자 (Ex. naver, google, ...)
    String getProvider();
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    //이메일
    String getEmail();
    //사용자 실명 (설정한 이름)
    String getName();
}