package com.field.muzi.web.admin.dto.course;

import com.field.muzi.setup.FileManagerEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseFirstSaveRequest {

    private String firstCourseTitle;
    private String courseType;
    private String courseDetail;
    private String terms1;
    private String terms2;
    private String terms3;
    private String terms4;
    private MultipartFile thumbnailImage;
    private String address;
    private String descriptionImage;
}
