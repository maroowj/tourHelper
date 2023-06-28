package com.field.muzi.web.user.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateProfileRequest {

    private String memberSeq;
    private String snsId;
    private String name;
    private String tel;
    private String preference1;
    private String preference2;
    private String country;
}
