package com.my.studyrecordsecure.controller.response;

import com.my.studyrecordsecure.domain.StudyRecordEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudyListViewResponse {


    private Long studyId;
    private String memberName;
    private String studyDay; // 미래시간 x 현재,과거에만 선택
    private String startTime;
    private int studyMins;
    private String endStudyDay;
    private String contents;


    public StudyListViewResponse(StudyRecordEntity study){
        this.studyId = study.getId();
        this.memberName = study.getMember().getName();
        this.studyDay=study.getStudyDay().toString();
        this.startTime=study.getStartTime().toString();
        this.studyMins=study.getStudyMins();
        this.endStudyDay=study.getEndStudyDay().toString();
        this.contents=study.getContents();


    }
}
