package com.field.muzi.web.user.controller.API;

import com.field.muzi.web.user.dto.study.StudyDetailsResponse;
import com.field.muzi.web.user.dto.study.StudyFileUploadRequest;
import com.field.muzi.web.user.dto.study.StudyListResponse;
import com.field.muzi.web.user.dto.study.StudySaveRequest;
import com.field.muzi.web.user.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class StudyAPI {

    private final StudyService studyService;

    // 유학 목록
    @GetMapping("/study")
    public List<StudyListResponse> studyList() {
        return studyService.studyList();
    }

    // 유학 상세보기
    @GetMapping("/study/{studySeq}")
    public StudyDetailsResponse studyDetails(@PathVariable("studySeq") String studySeq) {
        return studyService.studyDetails(studySeq);
    }

    // 유학 신청하기
    @PostMapping("/study")
    public void createStudy(@RequestBody StudySaveRequest request) {
        studyService.createStudy(request);
    }

    // 파일 제출하기
    @PutMapping("/study")
    public void fileUpload(StudyFileUploadRequest request) {
        studyService.fileUpload(request);
    }

    // 유학 신청 취소하기
    @DeleteMapping("/study/{studySeq}")
    public void deleteStudy(@PathVariable("studySeq") String studySeq) {
        studyService.deleteStudy(studySeq);
    }
}
