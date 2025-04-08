package com.my.studyrecordsecure.repository;


import com.my.studyrecordsecure.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByLoginId(String loginId);

    List<MemberEntity> findByRoleNot(MemberEntity.Role role);

    Optional<MemberEntity> findByProviderAndProviderId(String provider, String providerId);
}
