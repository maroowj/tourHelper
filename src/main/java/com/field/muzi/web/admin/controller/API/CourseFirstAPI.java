package com.field.muzi.web.admin.controller.API;

import com.field.muzi.domain.entity.CompanyEntity;
import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.web.admin.dto.company.CompanySaveRequest;
import com.field.muzi.web.admin.dto.company.CompanyUpdateRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstLIstResponse;
import com.field.muzi.web.admin.dto.course.CourseFirstListRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstSaveRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstUpdateRequest;
import com.field.muzi.web.admin.service.CompanyService;
import com.field.muzi.web.admin.service.CourseFirstService;
import com.field.muzi.web.user.dto.course.AllCourseListResponse;
import com.field.muzi.web.user.dto.course.FirstCourseDetailResponse;
import com.field.muzi.web.user.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class CourseFirstAPI {

    private final CourseFirstService courseFirstService;
    private final CourseService courseService;

    // 1차코스 목록 조회
    @GetMapping("/course/first/list")
    public Page<CourseFirstLIstResponse> courseFirstList(CourseFirstListRequest request, @PageableDefault(sort = "firstCourseSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        return courseFirstService.courseFirstList(request, pageable);
    }

    // 1차코스 신규 등록
    @PostMapping("/course/first/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void courseFirstSave(CourseFirstSaveRequest request) throws Exception {
        courseFirstService.courseFirstSave(request);
    }

    // 1차코스 상세 조회
    @GetMapping("/course/first/detail")
    public FirstCourseDetailResponse courseFirstDetail(String firstCourseSeq) {
        return courseService.firstCourseDetail(firstCourseSeq);
    }

    // 1차코스 수정
    @PostMapping("/course/first/update")
    public void courseFirstUpdate(CourseFirstUpdateRequest request) throws ParseException {
        courseFirstService.courseFirstUpdate(request);
    }

    //1차코스 삭제
    @PostMapping("/course/first/delete")
    public void firstCourseDelete(String firstCourseSeq) {
        courseFirstService.firstCourseDelete(firstCourseSeq);
    }

    //1차코스 복제
    @PostMapping("/course/first/copy")
    public void firstCourseCopy(String firstCourseSeq) {
        courseFirstService.firstCourseCopy(firstCourseSeq);
    }

}
