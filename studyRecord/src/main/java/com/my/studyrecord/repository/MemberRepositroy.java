package com.my.studyrecord.repository;

import com.my.studyrecord.domain.MemberEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByLoginId(String loginId);

    List<MemberEntity> findByRoleNot(MemberEntity.Role role);
}
