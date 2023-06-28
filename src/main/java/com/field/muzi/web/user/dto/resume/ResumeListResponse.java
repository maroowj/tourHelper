package com.field.muzi.web.user.dto.resume;

import com.field.muzi.domain.resume.ResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeListResponse {

    private String resumeSeq;
    private String company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    private int workDays;
    private int workYears;
    private int salary;
    private int working;

    public ResumeListResponse(ResumeEntity resume, int workDays, int workYears) {
        this.setResumeSeq(resume.getResumeSeq());
        this.setCompany(resume.getCompany());
        this.setJoinDate(resume.getJoinDate());
        this.setOutDate(resume.getOutDate());
        this.setWorkDays(workDays);
        this.setWorkYears(workYears);
        this.setSalary(resume.getSalary());
        this.setWorking(resume.getWorking());
    }
}
