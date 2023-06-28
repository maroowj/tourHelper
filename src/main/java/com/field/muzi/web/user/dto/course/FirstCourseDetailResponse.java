package com.field.muzi.web.user.dto.course;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirstCourseDetailResponse {

    private String firstCourseSeq;
    private String firstCourseTitle;
    private Map<String,Object> courseType;
    private String courseDetail;
    private String terms1;
    private String terms2;
    private String terms3;
    private String terms4;
    private Img thumbnailImage;
    private String address;
    private String description;

    @Data
    @AllArgsConstructor
    public static class Img {
        private String fileSeq;
        private String fileName;
        private String url;
    }
}
