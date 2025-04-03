package com.my.studyrecord.controller.request;

import com.my.studyrecord.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AddEditMemberRequest {
    private String id;
    private String pw;
    private String name;

    public MemberEntity toEntity( AddEditMemberRequest dto) {
        return MemberEntity.builder()
                        .loginId(dto.getId())
                        .name(dto.getName())
                        .password(dto.getPw()).build();
    }

}
