package com.field.muzi.web.user.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequest {

    MultipartFile file;

    private String title;
    private String content;
    private String category;
}
