package com.field.muzi.web.user.dto.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyFileUploadRequest {

    private String studySeq;
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
    private MultipartFile file5;
    private MultipartFile file6;
    private MultipartFile file7;
    private MultipartFile file8;
    private MultipartFile file9;
    private MultipartFile file10;

}
