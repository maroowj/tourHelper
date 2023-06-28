package com.field.muzi.web.user.dto.study;

import com.field.muzi.domain.study.StudyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyListResponse {

    private String studySeq;
    private String school;
    private String period;
    private int step;

    public StudyListResponse(StudyEntity study) {
        this.setStudySeq(study.getStudySeq());
        this.setSchool(study.getSchool().getFirstCourseTitle());
        this.setPeriod(study.getPeriod());
        this.setStep(study.getStep());
    }
}
