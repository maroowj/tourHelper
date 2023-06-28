package com.field.muzi.web.user.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeUpdateRequest {

    private String resumeSeq;
    private String company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    private int salary;
    private int working;
}
