package com.field.muzi.web.admin.dto.course;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseSecondSaveRequest {
    private MultipartFile thumbnailImage;

    private String secondCourseTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String course;
    private String address;
    private String category;
//    private int runningTime;
    private List<String> reservationDay;
//    private int cost;
    private String costOption;
    private String country;

    private String descriptionImage;

}
