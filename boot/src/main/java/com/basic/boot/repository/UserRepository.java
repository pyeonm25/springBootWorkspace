package com.basic.boot.repository;

import com.basic.boot.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
                                                     //<userEnitiy클래스이름 , PK 자료형타입>
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
