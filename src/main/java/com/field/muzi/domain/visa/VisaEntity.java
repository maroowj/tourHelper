package com.field.muzi.domain.visa;

import com.field.muzi.domain.base.BaseTimeEntity;
import com.field.muzi.domain.base.SeqManager;
import com.field.muzi.domain.entity.MemberEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Table(name = "visa")
@DynamicInsert
@DynamicUpdate
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class VisaEntity extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_visa")
    @GenericGenerator(name = "seq_manager_visa", strategy = "com.field.muzi.domain.base.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "visa_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String visaSeq;

    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private MemberEntity member;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private int accept; // 0: 신청, 1: 승인, 2: 완료

    @Type(type = "json")
    @Column(columnDefinition = "JSON")
    private Map<String, Object> content;

    @Column(nullable = false, columnDefinition = "int(7) default 0")
    private int point;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 30)
    private String phone;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(length = 255)
    private String address1;

    @Column(length = 255)
    private String address2;

    @Column(length = 10)
    private String postCode;

    @Column(length = 255)
    private String company;

    @Column(columnDefinition="timestamp(6)")
    private Date joinDate;

    @Column(columnDefinition="timestamp(6)")
    private Date agreedDate1;

    @Column(columnDefinition="timestamp(6)")
    private Date agreedDate2;

    @Column(columnDefinition="TEXT")
    private String comment;

    public static VisaEntity create(MemberEntity member, String name, String phone, String country, String address1,
                                    String address2, String postCode, String company, Date joinDate) {
        VisaEntity visa = new VisaEntity();
        visa.setMember(member);
        visa.setName(name);
        visa.setPhone(phone);
        visa.setCountry(country);
        visa.setAddress1(address1);
        visa.setAddress2(address2);
        visa.setPostCode(postCode);
        visa.setCompany(company);
        visa.setJoinDate(joinDate);
        visa.setPoint(0);
        visa.setAccept(0);
        return visa;
    }

    public void accept() {
        setAccept(1);
    }

    public void complete(Map<String, Object> content, int point) {
        setContent(content);
        setPoint(point);
        setAccept(2);
    }

    public void updateComment(String comment) {
        setComment(comment);
    }
}
