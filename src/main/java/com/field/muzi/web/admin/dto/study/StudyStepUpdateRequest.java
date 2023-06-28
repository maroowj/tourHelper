package com.field.muzi.web.admin.dto.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyStepUpdateRequest {

    private String studySeq;
    private int step;
    private String document;
    private String activeFileNumber;
    private String passport;
    private String state;
}
