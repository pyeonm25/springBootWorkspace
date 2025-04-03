package com.my.studyrecord.repository;

import com.my.studyrecord.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByLoginId(String loginId);
}
