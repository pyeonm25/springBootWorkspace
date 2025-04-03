package com.my.studyrecord.controller;

import com.my.studyrecord.controller.request.AddEditMemberRequest;
import com.my.studyrecord.domain.MemberEntity;
import com.my.studyrecord.service.MemberService;
import com.my.studyrecord.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    // form 관련된거 : 회원 추가 , 회원 수정 , getMapping
    private final MemberService memberService;
    private final StudyRecordService recordService;
    @GetMapping({"" , "/"})
    public String joinForm(){
        return "member/joinForm";
    }

    @PostMapping({"","/"})
    public String addMember(@ModelAttribute AddEditMemberRequest request) {
        log.trace("request member ={}" , request);
        try {
            memberService.createMember(request);
        }catch (Exception e) {
            log.error("errMSG={}", e.getMessage());
        }
        return "redirect:/";
    }



}
