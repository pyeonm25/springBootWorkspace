package com.my.studyrecordsecure.controller;


import com.my.studyrecordsecure.controller.request.AddStudyRequest;
import com.my.studyrecordsecure.controller.response.StudyListViewResponse;
import com.my.studyrecordsecure.controller.response.StudyRecordResponse;
import com.my.studyrecordsecure.domain.MemberEntity;
import com.my.studyrecordsecure.domain.StudyRecordEntity;
import com.my.studyrecordsecure.service.MemberService;
import com.my.studyrecordsecure.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final MemberService memberService;
    private final StudyRecordService recordService;

    @GetMapping({"/records"})
    public String getAllStudy(Model model) {
        //List<StudyRecordEntity> studyList = recordService.getAllRecordsByFetch();
        List<StudyRecordEntity> studyList = recordService.getAllRecordsByGraph();
        //List<StudyRecordEntity> studyList = recordService.getAllRecordsByQuery();
        List<StudyListViewResponse> list = studyList.stream().map(study -> new StudyListViewResponse(study)).collect(Collectors.toList());
        if(list.size() == 0) {
            return "/home";
        }
        model.addAttribute("list" , list);

        return "study/list";
    }

    @GetMapping({"","/"})
    public String createStudyRecord(Model model) {

        try {
            List<MemberEntity> members = memberService.getAllMembers();
            model.addAttribute("curdate" , LocalDate.now());
            model.addAttribute("curtime" , LocalTime.now());
            model.addAttribute("members" , members);
        }catch (Exception e){
            log.error("error={}",e.getMessage());
            return "redirect:/study/records";
        }

        return "study/addForm";
    }


    @PostMapping({"","/"})
    public String addRecord(AddStudyRequest studyRequest) {
        try{
            log.info("addRecord id={}",studyRequest);
            
            if (studyRequest.getLoginId() == null || studyRequest.getLoginId().isEmpty()) {
                throw new IllegalArgumentException("로그인 아이디가 필요합니다 ");
            }

            if (studyRequest.getStudyDay().isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("미래 시간 기록은 안된다");
            }
            
            MemberEntity member = memberService.getMemberByLoginId(studyRequest.getLoginId());
            recordService.saveRecord(studyRequest, member);

        }catch (Exception e){
            e.printStackTrace();
            log.error("error={}",e.getMessage());
            return "redirect:/";
        }

        return "redirect:/study/records";
    }

    @GetMapping("/{id:[0-9]+}")
    public String getOneRecord(@PathVariable Long id, Model model){
        log.info("getOneRecord id={}",id);
        try {
            model.addAttribute("curdate", LocalDate.now());
            StudyRecordEntity record = recordService.getOneRecord(id);
            model.addAttribute("record", new StudyRecordResponse(record)) ;
        }catch (Exception e){
            log.error("error={}",e.getMessage());
            return "redirect:/study/records";
        }


        return "study/updateForm";
    }

    @PostMapping("/update")
    public String updateRecode(StudyRecordResponse studyRequest){
        log.info("updateRecode studyRequest{} ",studyRequest);
        try {

            recordService.update(studyRequest);
        }catch (Exception e){
            log.error("error={}",e.getMessage());
        }

        return "redirect:/study/records";
    }

    @ResponseBody
    @DeleteMapping ("{id}")
    public ResponseEntity deleteRecode(@PathVariable Long id){
        log.info("delete id={}",id);
        recordService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }
}


