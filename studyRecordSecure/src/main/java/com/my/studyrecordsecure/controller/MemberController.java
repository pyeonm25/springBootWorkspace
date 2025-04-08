package com.my.studyrecordsecure.controller;

import com.my.studyrecordsecure.controller.request.AddMemberRequest;
import com.my.studyrecordsecure.controller.response.MemberListViewResponse;
import com.my.studyrecordsecure.service.MemberService;
import com.my.studyrecordsecure.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    // form 관련된거 : 회원 추가 , 회원 수정 , getMapping
    private final MemberService memberService;
    private final StudyRecordService recordService;

    @GetMapping({ "/members"})
    public String getMembers(Model model) {
        List<MemberListViewResponse> members = memberService.getAllMembers().stream()
                .map( m -> new MemberListViewResponse(m)).toList();

        if(members.size() == 0) {
            return "member/joinForm";
        }
//        members.forEach( m -> log.info("member: " + m));
        model.addAttribute("list", members);
        return "member/list";

    }

    @PostMapping({"","/"})
    public String addMember(final @ModelAttribute AddMemberRequest member  , Model model) {
        log.info("request member ={}" , member);

        try {
            memberService.createMember(member);  // Updated method call

        } catch (Exception e) {
            log.error("errMSG={}", e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "member/joinForm";
        }
        return "redirect:/home";
    }

    @GetMapping("/del/{id}")
    public String deleteMember(@PathVariable Long id){
        log.info("delete member id ={}" ,id );
        try {
            memberService.deleteMemberById(id);  // Updated method call

        } catch (Exception e) {
            log.error("errMSG={}", e.getMessage());
        }
        return "redirect:/member/members";
    }

    @ResponseBody
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity  deleteMemberAjax(@PathVariable Long id){

        log.info("Deleting member with id: {}", id);
        try {
            memberService.deleteMemberById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting member {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
