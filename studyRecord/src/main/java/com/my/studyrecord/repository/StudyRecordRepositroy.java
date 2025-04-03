package com.my.studyrecord.repository;

import com.my.studyrecord.domain.MemberEntity;
import com.my.studyrecord.domain.StudyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRecordRepositroy extends JpaRepository<StudyRecordEntity, Long> {

}
