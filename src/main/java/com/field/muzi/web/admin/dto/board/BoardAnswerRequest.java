package com.field.muzi.web.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardAnswerRequest {

    MultipartFile file;
    MultipartFile image;
    private String memberSeq;
    private String title;
    private String content;
    private String category;
}
