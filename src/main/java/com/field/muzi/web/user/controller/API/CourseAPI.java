package com.field.muzi.web.user.controller.API;

import com.field.muzi.web.admin.dto.course.CourseFirstLIstResponse;
import com.field.muzi.web.admin.dto.course.CourseFirstListRequest;
import com.field.muzi.web.admin.dto.course.CourseSecondListRequest;
import com.field.muzi.web.admin.dto.course.CourseSecondListResponse;
import com.field.muzi.web.admin.service.CourseFirstService;
import com.field.muzi.web.admin.service.CourseSecondService;
import com.field.muzi.web.user.dto.course.*;
import com.field.muzi.web.user.service.CourseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CourseAPI {

    private final CourseService courseService;
    private final CourseFirstService courseFirstService;
    private final CourseSecondService courseSecondService;

    @GetMapping("/course/list")
    public AllCourseListResponse allCourseList(AllCourseListRequest request) {
        if(request.getSearchDate()==null && request.getReservationDay()==null) {
            return courseService.allCourseList(request);
        }else {
            return courseService.allCourseListForReservation(request);
        }
    }

    @GetMapping("/school")
    public Page<CourseFirstLIstResponse> courseFirstList(CourseFirstListRequest request, @PageableDefault(sort = "firstCourseSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        return courseFirstService.courseFirstList(request, pageable);
    }

    @GetMapping("/tour")
    public Page<CourseSecondListResponse> courseSecondList(CourseSecondListRequest request, Pageable pageable) {
        return courseSecondService.courseSecondList(request, pageable);
    }

    @GetMapping("/school/detail")
    public FirstCourseDetailResponse firstCourseDetail(String schoolSeq) {
        return courseService.firstCourseDetail(schoolSeq);
    }

    @GetMapping("/tour/{tourSeq}")
    public SecondCourseDetailResponse tourCourseDetail(@PathVariable("tourSeq") String tourSeq) {
        return courseService.secondCourseDetail(tourSeq);
    }

    @GetMapping("/course/category")
    public List<CategoryGroup> categoryGroupList() {
        return courseService.categoryGroupList();
    }
}
