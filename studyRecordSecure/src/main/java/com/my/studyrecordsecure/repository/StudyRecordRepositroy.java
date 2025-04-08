package com.my.studyrecordsecure.repository;

import com.my.studyrecordsecure.domain.StudyRecordEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudyRecordRepositroy extends JpaRepository<StudyRecordEntity, Long> {

    @Query("SELECT sr FROM StudyRecordEntity sr JOIN FETCH sr.member")
    List<StudyRecordEntity> findAllFetch();

    @EntityGraph(attributePaths = "member")
    List<StudyRecordEntity> findAll();


    @Query("SELECT sr FROM StudyRecordEntity sr INNER JOIN sr.member m")
    List<StudyRecordEntity> findAllQuery();


    // 이거 안하면 n+1 문제 나온다
    @EntityGraph(attributePaths = "member")
    Optional<StudyRecordEntity> findById(Long id);

    List<StudyRecordEntity> findByMemberId(Long memberId);


//    @Query(
//            value = "SELECT * FROM study_records r WHERE r.member_id = :memberId",
//            nativeQuery = true
//    )
//    List<StudyRecordEntity> SearchRecordByMemerId(@Param(value="memberId") Long memberId);

}
