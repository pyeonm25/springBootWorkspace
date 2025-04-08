package com.my.studyrecordsecure.config.oauth.provider;

import com.my.studyrecordsecure.config.oauth.OAuth2UserInfo;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    // attributes ={ id= , email= , name }
    private Map<String, Object> attributes;
    public NaverUserInfo(Map<String, Object> attributes ){
        this.attributes = attributes;
    }
    @Override
    public String getProviderId() {
        return (String)attributes.get("id");
    }
    @Override
    public String getProvider() {
        return "naver";
    }
    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }
    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}