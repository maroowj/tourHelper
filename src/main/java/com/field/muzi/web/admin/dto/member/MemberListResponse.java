package com.field.muzi.web.admin.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {

    private int rowNum;
    private String memberSeq;
    private String email;
    private String provider;
    private String name;
    private String phone;
    private String preference1;
    private String preference2;
    private String country;
}
