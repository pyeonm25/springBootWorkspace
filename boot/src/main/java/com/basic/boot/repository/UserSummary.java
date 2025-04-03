package com.basic.boot.repository;

import com.basic.boot.domain.UserAuthenticateEntity;

import java.util.List;

public interface UserSummary {
    String getUsername();
    String getEmail();
    List<UserAuthenticateEntity> getUserAuthenticates();
}
