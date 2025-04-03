package com.my.studyrecord.controller;

import com.my.studyrecord.service.MemberService;
import com.my.studyrecord.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final MemberService memberService;
    private final StudyRecordService recordService;

}

