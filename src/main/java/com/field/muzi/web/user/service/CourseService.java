package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.domain.entity.SecondCourseEntity;
import com.field.muzi.repository.FirstCourseRepository;
import com.field.muzi.repository.SecondCourseRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.user.dto.course.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final FirstCourseRepository firstCourseRepository;
    private final SecondCourseRepository secondCourseRepository;

    @Transactional
    public AllCourseListResponse allCourseList(AllCourseListRequest request) {
        AllCourseListResponse response = new AllCourseListResponse();

        Date today = new Date();
        request.setSearchDate(today);

        response.setFirstCourseList(firstCourseRepository.userFirstCourseList(request));
        response.setSecondCoursesList(secondCourseRepository.userSecondCourseList(request));


        return response;
    }

    @Transactional
    public AllCourseListResponse allCourseListForReservation(AllCourseListRequest request) {
        AllCourseListResponse response = new AllCourseListResponse();
        List<AllCourseListResponse.SecondCourse> emptySecondCourseList = new ArrayList<>();
        String category="";

        List<AllCourseListResponse.FirstCourse> firstCourseList = firstCourseRepository.userFirstCourseList(request);

        List<AllCourseListResponse.SecondCourse> secondCourseList = secondCourseRepository.userSecondCourseList(request);
        for(AllCourseListResponse.SecondCourse secondCourse:secondCourseList) {
            List<String> reservationDays = secondCourse.getReservationDay();
            for(String day:reservationDays) {
                if(request.getReservationDay().equals(day)){
                    emptySecondCourseList.add(secondCourse);
                }
            }
        }

        response.setFirstCourseList(firstCourseList);
        response.setSecondCoursesList(emptySecondCourseList);

        return response;
    }

    @Transactional
    public FirstCourseDetailResponse firstCourseDetail(String firstCourseSeq) {
        FirstCourseEntity firstCourse = EntityUtils.schoolThrowable(firstCourseRepository, firstCourseSeq);
        return firstCourseRepository.firstCourseDetail(firstCourseSeq);
    }

    @Transactional
    public SecondCourseDetailResponse secondCourseDetail(String secondCourseSeq) {
        SecondCourseEntity secondCourse = EntityUtils.tourThrowable(secondCourseRepository, secondCourseSeq);
        return secondCourseRepository.secondCourseDetail(secondCourseSeq);
    }

    @Transactional
    public List<CategoryGroup> categoryGroupList() {
        List<CategoryGroup> categoryGroupList = new ArrayList<>();

        List<CategoryGroup> originList = secondCourseRepository.categoryGroup();

        for(CategoryGroup cg:originList) {
            AllCourseListRequest request = new AllCourseListRequest();
            request.setCategory(cg.getCategory());
            List<AllCourseListResponse.SecondCourse> list = secondCourseRepository.userSecondCourseList(request);
            if(list.size()>0) {
                CategoryGroup category = cg;
                categoryGroupList.add(category);
            }
        }

        return categoryGroupList;
    }
}
