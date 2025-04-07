package com.my.security1.repository;

import com.my.security1.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
