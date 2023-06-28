package com.field.muzi.web.admin.dto.study;

import com.field.muzi.domain.study.StudyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyListResponseAdmin {

    private int rowNum;
    private String studySeq;
    private String school;
    private String period;
    private String topik;
    private int step;
    private String name;
    private String gender;
    private String phone;
    private String country;
    private String document;
    private String etc;
}
