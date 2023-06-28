package com.field.muzi.domain.resume;

import com.field.muzi.domain.base.BaseTimeEntity;
import com.field.muzi.domain.base.SeqManager;
import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.setup.FileManagerEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Table(name = "resume")
@DynamicInsert
@DynamicUpdate
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ResumeEntity extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_resume")
    @GenericGenerator(name = "seq_manager_resume", strategy = "com.field.muzi.domain.base.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "rsme_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String resumeSeq;

    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private MemberEntity member;

    @Column(nullable = false, length = 200)
    private String company; // 기간

    @Column(columnDefinition="timestamp(6)")
    private Date joinDate;

    @Column(columnDefinition="timestamp(6)")
    private Date outDate;

    @Column
    private int salary;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private int working;  // 0: 퇴사, 1: 휴직, 2: 재직

    public static ResumeEntity create(MemberEntity member, String company, Date joinDate, Date outDate,
                                      int salary, int working) {
        ResumeEntity resume = new ResumeEntity();
        resume.setMember(member);
        resume.setCompany(company);
        resume.setJoinDate(joinDate);
        resume.setOutDate(outDate);
        resume.setSalary(salary);
        resume.setWorking(working);
        return resume;
    }

    public void update(String company, Date joinDate, Date outDate,
                       int salary, int working) {
        setCompany(company);
        setJoinDate(joinDate);
        setOutDate(outDate);
        setSalary(salary);
        setWorking(working);
    }

}
