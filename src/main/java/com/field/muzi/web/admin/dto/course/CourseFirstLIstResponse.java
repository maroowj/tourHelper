package com.field.muzi.web.admin.dto.course;

import com.field.muzi.web.user.dto.course.AllCourseListResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseFirstLIstResponse {

        private int rowNum;
        private String firstCourseSeq;
        private String firstCourseTitle;
        private Map<String,Object> courseType;
        private String courseDetail;
        private String terms1;
        private String terms2;
        private String terms3;
        private String terms4;
        private AllCourseListResponse.Img thumbnailImage;
        private String address;
        private String description;
}
