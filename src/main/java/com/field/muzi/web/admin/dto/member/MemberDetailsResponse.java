package com.field.muzi.web.admin.dto.member;

import com.field.muzi.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailsResponse {

    private String memberSeq;
    private String email;
    private String provider;
    private String gender;
    private String name;
    private String phone;
    private String preference1;
    private String preference2;
    private String country;

    public MemberDetailsResponse(MemberEntity member) {
        this.setMemberSeq(member.getMemberSeq());
        this.setEmail(member.getMemberInfo().getEmail());
        this.setProvider(member.getProvider());
        if(member.getMemberInfo().getGender().equals("male")) {
            this.setGender("남성");
        }else if(member.getMemberInfo().getGender().equals("female")) {
            this.setGender("여성");
        }else {
            this.setGender("");
        }
        this.setName(member.getMemberInfo().getName());
        this.setPhone(member.getMemberInfo().getTel());
        this.setPreference1(member.getMemberInfo().getPreference1());
        this.setPreference2(member.getMemberInfo().getPreference2());
        this.setCountry(member.getMemberInfo().getCountry());
    }
}
