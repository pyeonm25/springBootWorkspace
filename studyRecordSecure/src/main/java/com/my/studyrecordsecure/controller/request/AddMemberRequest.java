package com.my.studyrecordsecure.controller.request;

import com.my.studyrecordsecure.domain.MemberEntity;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter  // 없으면 자동으로 값 안넣어줌
public class AddMemberRequest {
    private String id;
    private String email;
    private String pw;
    private String name;

    public MemberEntity toEntity(AddMemberRequest dto) {

        return MemberEntity.builder()
                        .loginId(dto.getId())
                         .name(dto.getName())
                        .email(dto.getEmail())
                        .password(dto.getPw()).build();
    }

}

