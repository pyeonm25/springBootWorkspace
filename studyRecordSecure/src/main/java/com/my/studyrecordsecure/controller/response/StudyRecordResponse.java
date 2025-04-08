package com.my.studyrecordsecure.controller.response;

import com.my.studyrecordsecure.domain.StudyRecordEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Setter
@ToString
public class StudyRecordResponse {
    private Long studyId;
    private String memberName;
    private LocalDate studyDay;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
    private Long memberId;


    public StudyRecordResponse(StudyRecordEntity study){
        this.studyId = study.getId();
        this.memberName = study.getMember().getName();
        this.studyDay=study.getStudyDay();
        this.startTime=study.getStartTime();
        this.studyMins=study.getStudyMins();
        this.contents=study.getContents();
        this.memberId=study.getMember().getId();

    }
}
