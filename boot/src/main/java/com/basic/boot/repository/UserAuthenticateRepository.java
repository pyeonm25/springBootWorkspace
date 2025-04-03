package com.basic.boot.repository;

import com.basic.boot.domain.UserAuthenticateEntity;
import com.basic.boot.domain.UserAuthenticateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticateRepository extends JpaRepository<UserAuthenticateEntity, UserAuthenticateId> {
}
