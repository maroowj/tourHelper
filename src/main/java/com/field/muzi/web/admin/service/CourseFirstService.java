package com.field.muzi.web.admin.service;

import com.field.muzi.domain.entity.CompanyEntity;
import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.repository.CompanyRepository;
import com.field.muzi.repository.FirstCourseRepository;
import com.field.muzi.repository.ReservationRepository;
import com.field.muzi.repository.SecondCourseRepository;
import com.field.muzi.repository.setup.FileManagerRepository;
import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.course.CourseFirstLIstResponse;
import com.field.muzi.web.admin.dto.course.CourseFirstListRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstSaveRequest;
import com.field.muzi.web.admin.dto.course.CourseFirstUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseFirstService {

    private final CompanyRepository companyRepository;
    private final FirstCourseRepository firstCourseRepository;
    private final SecondCourseRepository secondCourseRepository;
    private final FileManagerRepository fileManagerRepository;
    private final ReservationRepository reservationRepository;

    private final FileHandler fileHandler;

    // 1차코스 목록 조회
    @Transactional
    public Page<CourseFirstLIstResponse> courseFirstList(CourseFirstListRequest request, Pageable pageable) {

        Page<CourseFirstLIstResponse> result = firstCourseRepository.firstCourseList(request, pageable);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (CourseFirstLIstResponse response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    // 1차코스 신규 등록
    @Transactional
    public void courseFirstSave(CourseFirstSaveRequest request) throws Exception {
        JSONParser jsonParser = new JSONParser();

        Map<String, Object> courseType = new HashMap<>();
        if (request.getCourseType() != null && !request.getCourseType().equals("")) {
            courseType = (Map<String, Object>) jsonParser.parse(request.getCourseType());
        }

        List<MultipartFile> thumbnail = new ArrayList<>();

        FirstCourseEntity firstCourse = firstCourseRepository.save(
                FirstCourseEntity.builder()
                        .firstCourseTitle(request.getFirstCourseTitle())
                        .courseType(courseType)
                        .courseDetail(request.getCourseDetail())
                        .terms1(request.getTerms1())
                        .terms2(request.getTerms2())
                        .terms3(request.getTerms3())
                        .terms4(request.getTerms4())
                        .address(request.getAddress())
                        .descriptionImage(request.getDescriptionImage())
                        .build()
        );

        if (request.getThumbnailImage() != null && !request.getThumbnailImage().isEmpty()) {

            thumbnail.add(request.getThumbnailImage());
            FileManagerEntity img = fileManagerRepository.save(fileHandler.parse(thumbnail, "thum_img").get(0));
            firstCourse.setThumbnailImage(img);
        }
    }

    // 1차코스 상세 조회
    @Transactional
    public FirstCourseEntity courseFirstDetail(String firstCourseSeq) {
        return firstCourseRepository.findByFirstCourseSeq(firstCourseSeq);
    }

    // 1차코스 수정
    @Transactional
    public void courseFirstUpdate(CourseFirstUpdateRequest request) throws ParseException {
        FirstCourseEntity entity = firstCourseRepository.findByFirstCourseSeq(request.getFirstCourseSeq());

        JSONParser jsonParser = new JSONParser();

        Map<String, Object> courseType = new HashMap<>();
        if (request.getCourseType() != null && !request.getCourseType().equals("")) {
            courseType = (Map<String, Object>) jsonParser.parse(request.getCourseType());
        }

        if (request.getFirstCourseTitle() != null) {
            entity.updateFirstCourseTitle(request.getFirstCourseTitle());
        }
        if (request.getCourseType() != null) {
            entity.updateCourseType(courseType);
        }
        if (request.getCourseDetail() != null) {
            entity.updateCourseDetail(request.getCourseDetail());
        }
        if (request.getTerms1() != null) {
            entity.updateTerms1(request.getTerms1());
        }
        if (request.getTerms2() != null) {
            entity.updateTerms2(request.getTerms2());
        }
        if (request.getTerms3() != null) {
            entity.updateTerms3(request.getTerms3());
        }
        if (request.getTerms4() != null) {
            entity.updateTerms4(request.getTerms4());
        }
        if (request.getAddress() != null) {
            entity.updateAddress(request.getAddress());
        }
        if (request.getDescriptionImage() != null) {
            entity.updateDescriptionImage(request.getDescriptionImage());
        }
        if (request.getThumbnailImage() != null && !request.getThumbnailImage().isEmpty()) {
            List<MultipartFile> imgs = new ArrayList<>();
            imgs.add(request.getThumbnailImage());
            try {
                FileManagerEntity img = fileManagerRepository.save(fileHandler.parse(imgs, "thum_img").get(0));
                entity.updateThumbNailImage(img);
            } catch (Exception e) {
                throw new RuntimeException("이미지 수정에 실패했습니다.");
            }
        }
    }

    // 1차코스 삭제
    @Transactional
    public void firstCourseDelete(String firstCourseSeq) {
        FirstCourseEntity entity = firstCourseRepository.findByFirstCourseSeq(firstCourseSeq);
        entity.deleteFirstCourse();
    }

    // 1차코스 복사
    @Transactional
    public void firstCourseCopy(String firstCourseSeq) {
        FirstCourseEntity origin = firstCourseRepository.findByFirstCourseSeq(firstCourseSeq);

        boolean applicant;
        boolean applicantDetail;
        boolean guestNumber;
        boolean managerPhone;
        boolean vehicle;
        boolean etc;
        boolean requirement;

        FirstCourseEntity newCourse = firstCourseRepository.save(
                FirstCourseEntity.builder()
                        .firstCourseTitle(origin.getFirstCourseTitle())
                        .build()
        );

    }

}
