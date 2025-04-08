package com.my.studyrecordsecure.controller.response;

import com.my.studyrecordsecure.domain.MemberEntity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberListViewResponse {
    private final Long id;
    private final String loginId;
    private final String name;
    private final String role;
    public MemberListViewResponse(MemberEntity member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.role = member.getRole().toString();

    }
}
