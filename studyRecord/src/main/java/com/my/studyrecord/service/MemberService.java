package com.my.studyrecord.service;

import com.my.studyrecord.controller.request.AddEditMemberRequest;
import com.my.studyrecord.domain.MemberEntity;
import com.my.studyrecord.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기전용 트렌젝셔널 -> sql 저장소 빠진거
public class MemberService {
    private final MemberRepositroy memberRepository;

    @Transactional // 읽기 , 쓰기(삭제, 수정 )
    public void createMember(AddEditMemberRequest memberDto ) throws IllegalStateException{
        log.trace("memberDto ={}" ,memberDto );
        MemberEntity m = getMemberId(memberDto.toEntity(memberDto));
        memberRepository.save(m);


    }

    private MemberEntity getMemberId(MemberEntity member) throws IllegalStateException{
        log.trace("getMemberId ={}" ,member );
        if(memberRepository.findByLoginId(member.getLoginId()) != null){
            throw new IllegalStateException("이미 존재하는 회원 아이디가 있습니다");
        }
        return member;
    }
}
