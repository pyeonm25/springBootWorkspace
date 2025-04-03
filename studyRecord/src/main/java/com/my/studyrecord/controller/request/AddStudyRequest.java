package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.MemberEntity;
import com.my.studyrecord.domain.StudyRecordEntity;
import lombok.*;

import java.time.LocalTime;
import java.time.LocalDate;

// 화면에서 받아오는값
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddStudyRequest {
    private String loginId;
    private LocalDate studyDay; // 미래시간 x 현재,과거에만 선택
    private LocalTime startTime;
    private int studyMins;
    private String contents;

    public StudyRecordEntity toEntity(AddStudyRequest dto , MemberEntity member) {
        return StudyRecordEntity.builder()
                .member(member)
                .studyDay(dto.getStudyDay())
                .startTime(dto.getStartTime())
                .studyMins(dto.getStudyMins())
                .contents(dto.getContents())
                .build();
    }
}
