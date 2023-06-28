package com.field.muzi.web.user.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponse {

    private String boardSeq;
    private String content;
    private String category;
    private String createDate;
    private boolean answer;
    private Image image;
    private File file;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String fileName;
        private String fileUrl;
        private String fileExtension;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class File {
        private String fileName;
        private String fileUrl;
        private String fileExtension;
    }
}
