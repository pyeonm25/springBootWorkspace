package com.my.studyrecord.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // jpa 만 내 객체를 생성할 수 있게
@Entity
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

    public String getEndStudyDay(){
        String pattern = "yyyy/MM/dd HH:mm";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        String data =  endStudyTime.format(DateTimeFormatter.ofPattern(pattern));

        return data;
    }

}
