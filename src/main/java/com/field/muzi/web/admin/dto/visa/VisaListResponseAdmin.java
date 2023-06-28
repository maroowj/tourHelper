package com.field.muzi.web.admin.dto.visa;

import com.field.muzi.domain.visa.VisaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaListResponseAdmin {

    private int rowNum;
    private String visaSeq;
    private Map<String, Object> content;
    private int accept;
    private int point;
    private String name;
    private String phone;
    private String country;
    private String address1;
    private String address2;
    private String postCode;
    private String company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
    private String email;
    private String comment;

    public VisaListResponseAdmin(VisaEntity visa) {
        this.setVisaSeq(visa.getVisaSeq());
        this.setContent(visa.getContent());
        this.setAccept(visa.getAccept());
        this.setPoint(visa.getPoint());
        this.setName(visa.getName());
        this.setPhone(visa.getPhone());
        this.setCountry(visa.getCountry());
        this.setAddress1(visa.getAddress1());
        this.setAddress2(visa.getAddress2());
        this.setPostCode(visa.getPostCode());
        this.setCompany(visa.getCompany());
        this.setJoinDate(visa.getJoinDate());
        this.setEmail(visa.getMember().getMemberInfo().getEmail());
        this.setComment(visa.getComment());
    }
}
