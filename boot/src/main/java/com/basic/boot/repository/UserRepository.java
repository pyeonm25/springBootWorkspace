package com.basic.boot.repository;

import com.basic.boot.domain.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                   //<userEnitiy클래스이름 , PK 자료형타입>
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // selecct * from users where email = ?
    UserEntity findByEmail(String email);
    // select * from users where username = ? and password =?
    boolean existsByUsernameAndPassword(String username, String password);

    //유저 table 유저인증 table join => 유저 객체안에 있는 list 이름 넣어주면 끝
    @EntityGraph( attributePaths = {"userAuthenticates"})
    List<UserSummary> findBy();

    @EntityGraph(attributePaths = "userAuthenticates")
    UserSummary findByUsername(String username);
}
