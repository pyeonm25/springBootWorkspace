package com.my.studyrecordsecure.service;

import com.my.studyrecordsecure.controller.request.AddMemberRequest;
import com.my.studyrecordsecure.domain.MemberEntity;
import com.my.studyrecordsecure.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기전용 트렌젝셔널 -> sql 저장소 빠진거
public class MemberService {
    private final MemberRepositroy memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // 읽기 , 쓰기(삭제, 수정 )
    public void createMember(AddMemberRequest memberDto ) throws IllegalStateException{
        log.info("memberDto ={}" ,memberDto );
        // 비밀번호 암호화
        memberDto.setPw(bCryptPasswordEncoder.encode(memberDto.getPw()));
        MemberEntity m = getMemberId(memberDto.toEntity(memberDto));
        memberRepository.save(m);


    }
    public  MemberEntity getMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id가 존재하지않습니다"));
    }
    public  MemberEntity getMemberByLoginId(String loginId){
        MemberEntity member = memberRepository.findByLoginId(loginId);
        if(member == null){
            throw new IllegalStateException("loginId가 존재하지않습니다");
        }
        return member;
    }

    public List<MemberEntity> getAllMembers(){
       // return memberRepository.findAll();
        return memberRepository.findByRoleNot(MemberEntity.Role.ROLE_ADMIN);
    }

    private MemberEntity getMemberId(MemberEntity member) throws IllegalStateException{
        log.info("getMemberId ={}" ,member );
        if(memberRepository.findByLoginId(member.getLoginId()) != null){
            throw new IllegalStateException("이미 존재하는 회원 아이디가 있습니다");
        }
        return member;
    }

    @Transactional
    public void deleteMemberById(Long id) throws IllegalStateException{
        MemberEntity delMember = memberRepository.findById(id).orElse(null);
        log.info("dlete delMember ={}" , delMember );
        //  studyRecordService.deleteAllRecordByMember(delMember);
        if(delMember != null) {
            memberRepository.delete(delMember);
        }else{
            throw new IllegalStateException("삭제할 id가 존재하지않습니다");
        }

    }

}
