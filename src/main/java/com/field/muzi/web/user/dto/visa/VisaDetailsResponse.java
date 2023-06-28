package com.field.muzi.web.user.dto.visa;

import com.field.muzi.domain.visa.VisaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisaDetailsResponse {

    private String visaSeq;
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

    public VisaDetailsResponse(VisaEntity visa) {
        this.setVisaSeq(visa.getVisaSeq());
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
    }
}
