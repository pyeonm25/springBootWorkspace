package com.my.studyrecordsecure.domain;

import com.my.studyrecordsecure.controller.response.StudyRecordResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "member")
@Table(name="study_records")
public class StudyRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_id")
    private Long id; // study_id

    private LocalDate studyDay; // 미래시간 선택x 현재, 과거 선택
    private LocalTime startTime; // 오후 6:10
    private int studyMins; // 40
    @Lob
    private String contents;

    // fk 값을 가지는가? (member_id),  == 연관관계의 주인 == @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private MemberEntity member; //fk => member_id



    public String getEndStudyDay() {
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        return endStudyTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    @Builder
    public StudyRecordEntity(LocalDate studyDay, LocalTime startTime, int studyMins, String contents, MemberEntity member) {
        this.studyDay = studyDay;
        this.startTime = startTime;
        this.studyMins = studyMins;
        this.contents = contents;
        this.member = member;
    }


    public void updateRecord( StudyRecordResponse dto) {
        this.studyDay = dto.getStudyDay();
        this.startTime = dto.getStartTime();
        this.studyMins = dto.getStudyMins();
        this.contents = dto.getContents();
    }
}
