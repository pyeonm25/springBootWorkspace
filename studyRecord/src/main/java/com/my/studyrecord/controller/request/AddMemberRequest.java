package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.MemberEntity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter  // 없으면 자동으로 값 안넣어줌
public class AddMemberRequest {
    private String id;
    private String pw;
    private String name;

    public MemberEntity toEntity( AddMemberRequest dto) {

        return MemberEntity.builder()
                        .loginId(dto.getId())
                        .name(dto.getName())
                        .password(dto.getPw()).build();
    }

}

