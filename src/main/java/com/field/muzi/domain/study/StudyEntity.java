package com.field.muzi.domain.study;

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
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Table(name = "study")
@DynamicInsert
@DynamicUpdate
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class StudyEntity extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_study")
    @GenericGenerator(name = "seq_manager_study", strategy = "com.field.muzi.domain.base.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "stdy_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String studySeq;

    @ManyToOne
    @JoinColumn(name = "memberSeq")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "schoolSeq")
    private FirstCourseEntity school;

    @Column(length = 200)
    private String period; // 기간

    @Column(length = 200)
    private String topik; // 한국어 능력시험

    @Column(nullable = false, columnDefinition = "int(7) default 0")
    private int step;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column
    private String phone;

    @Column
    private String country;

    @Column(columnDefinition="timestamp(6)")
    private Date agreedDate1;

    @Column(columnDefinition="timestamp(6)")
    private Date agreedDate2;

    @Column
    private String document;

    @Column
    private String birth;

    @Column
    private String passport;

    @Column
    private String state;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file1")
    private FileManagerEntity file1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file2")
    private FileManagerEntity file2;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file3")
    private FileManagerEntity file3;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file4")
    private FileManagerEntity file4;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file5")
    private FileManagerEntity file5;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file6")
    private FileManagerEntity file6;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file7")
    private FileManagerEntity file7;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file8")
    private FileManagerEntity file8;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file9")
    private FileManagerEntity file9;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file10")
    private FileManagerEntity file10;

    @Column(length = 200)
    private String etc;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean submit;

    @Column
    private String activeFileNumber;

    @Column(columnDefinition="timestamp(6)")
    private Date deletedDate;

    public static StudyEntity create(MemberEntity member, FirstCourseEntity school, String period, String topik,
                                     String name, String gender, String phone, String country, String etc, String birth) {
        StudyEntity study = new StudyEntity();
        study.setMember(member);
        study.setSchool(school);
        study.setPeriod(period);
        study.setTopik(topik);
        study.setName(name);
        study.setGender(gender);
        study.setPhone(phone);
        study.setCountry(country);
        study.setEtc(etc);
        study.setAgreedDate1(new Date());
        study.setAgreedDate2(new Date());
        study.setSubmit(false);
        study.setStep(0);
        study.setBirth(birth);
        study.setState("접수");
        return study;
    }

    public void uploadFile1(FileManagerEntity file1) { this.setFile1(file1); }
    public void uploadFile2(FileManagerEntity file2) { this.setFile2(file2); }
    public void uploadFile3(FileManagerEntity file3) { this.setFile3(file3); }
    public void uploadFile4(FileManagerEntity file4) { this.setFile4(file4); }
    public void uploadFile5(FileManagerEntity file5) { this.setFile5(file5); }
    public void uploadFile6(FileManagerEntity file6) { this.setFile6(file6); }
    public void uploadFile7(FileManagerEntity file7) { this.setFile7(file7); }
    public void uploadFile8(FileManagerEntity file8) { this.setFile8(file8); }
    public void uploadFile9(FileManagerEntity file9) { this.setFile9(file9); }
    public void uploadFile10(FileManagerEntity file10) { this.setFile10(file10); }

    public void submit() {
        this.setSubmit(true);
    }

    public void delete() {
        setDeletedDate(new Date());
    }

    public void updateStep(int step, String document, String activeFileNumber, String passport, String state) {
        setStep(step);
        setDocument(document);
        setActiveFileNumber(activeFileNumber);
        if(activeFileNumber==null || activeFileNumber=="") {
            setSubmit(true);
        }else {
            setSubmit(false);
        }
        setPassport(passport);
        setState(state);
    }

}
