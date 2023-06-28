package com.field.muzi.web.user.dto.member;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.MemberInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileResponse {

    private String name;
    private String tel;
    private String preference1;
    private String preference2;
    private String country;
    private String gender;
    private String profileImage;
    private String platform;

    public MemberProfileResponse(MemberInfoEntity memberInfo, MemberEntity member) {
        this.setName(memberInfo.getName());
        this.setTel(memberInfo.getTel());
        this.setPreference1(memberInfo.getPreference1());
        this.setPreference2(memberInfo.getPreference2());
        this.setCountry(memberInfo.getCountry());
        this.setGender(memberInfo.getGender());
        this.setProfileImage(memberInfo.getProfileImage());
        this.setPlatform(member.getProvider());
    }
}
