package com.field.muzi.web.user.dto.study;

import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.setup.FileManagerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyDetailsResponse {

    private String studySeq;
    private String school;
    private String period;
    private String topik;
    private int step;
    private String document;
    private String activeFileNumber;
    private String name;
    private String gender;
    private String phone;
    private String country;
    private String file1;
    private String file1Url;
    private String file2;
    private String file2Url;
    private String file3;
    private String file3Url;
    private String file4;
    private String file4Url;
    private String file5;
    private String file5Url;
    private String file6;
    private String file6Url;
    private String file7;
    private String file7Url;
    private String file8;
    private String file8Url;
    private String file9;
    private String file9Url;
    private String file10;
    private String file10Url;
    private String etc;
    private boolean submit;
    private String birth;
    private String passport;
    private String state;

    public StudyDetailsResponse(StudyEntity study, String file1, String file1Url, String file2, String file2Url, String file3, String file3Url,
                                String file4, String file4Url, String file5, String file5Url, String file6, String file6Url, String file7,
                                String file7Url, String file8, String file8Url, String file9, String file9Url, String file10, String file10Url) {
        this.setStudySeq(study.getStudySeq());
        this.setSchool(study.getSchool().getFirstCourseTitle());
        this.setPeriod(study.getPeriod());
        this.setTopik(study.getTopik());
        this.setStep(study.getStep());
        this.setDocument(study.getDocument());
        this.setActiveFileNumber(study.getActiveFileNumber());
        this.setName(study.getName());
        this.setGender(study.getGender());
        this.setPhone(study.getPhone());
        this.setCountry(study.getCountry());
        this.setFile1(file1);
        this.setFile1Url(file1Url);
        this.setFile2(file2);
        this.setFile2Url(file2Url);
        this.setFile3(file3);
        this.setFile3Url(file3Url);
        this.setFile4(file4);
        this.setFile4Url(file4Url);
        this.setFile5(file5);
        this.setFile5Url(file5Url);
        this.setFile6(file6);
        this.setFile6Url(file6Url);
        this.setFile7(file7);
        this.setFile7Url(file7Url);
        this.setFile8(file8);
        this.setFile8Url(file8Url);
        this.setFile9(file9);
        this.setFile9Url(file9Url);
        this.setFile10(file10);
        this.setFile10Url(file10Url);
        this.setEtc(study.getEtc());
        this.setSubmit(study.isSubmit());
        this.setBirth(study.getBirth());
        this.setPassport(study.getPassport());
        this.setState(study.getState());
    }
}
