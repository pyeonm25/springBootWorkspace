package com.my.studyrecord.service;

import com.my.studyrecord.controller.request.AddStudyRequest;
import com.my.studyrecord.controller.response.StudyRecordResponse;
import com.my.studyrecord.domain.MemberEntity;
import com.my.studyrecord.domain.StudyRecordEntity;
import com.my.studyrecord.repository.StudyRecordRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecordService {
    private final StudyRecordRepositroy studyRecordRepositroy;

    public List<StudyRecordEntity> getAllRecordsByFetch() {
        return studyRecordRepositroy.findAllFetch();
    }
    public List<StudyRecordEntity> getAllRecordsByGraph() {
        return studyRecordRepositroy.findAll();
    }
    public List<StudyRecordEntity> getAllRecordsByQuery() {
        return studyRecordRepositroy.findAllQuery();
    }


    public StudyRecordEntity getOneRecord(Long id){
        StudyRecordEntity record = studyRecordRepositroy.findById(id).orElse(null);
        if(record == null) {
            throw new IllegalStateException("id가 존재하지않습니다");
        }
        return record;
    }

    @Transactional
    public StudyRecordEntity saveRecord(AddStudyRequest dto , MemberEntity member) {
        return studyRecordRepositroy.save(dto.toEntity(dto, member));
    }


    @Transactional
    public StudyRecordEntity update(StudyRecordResponse dto ) {
        StudyRecordEntity study = studyRecordRepositroy.findById(dto.getStudyId())
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지않습니다"));
        study.updateRecord(dto);
        return study;
    }
    @Transactional
    public void deleteRecord(Long id){
        studyRecordRepositroy.deleteById(id);
    }
}
