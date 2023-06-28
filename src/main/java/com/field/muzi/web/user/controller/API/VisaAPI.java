package com.field.muzi.web.user.controller.API;

import com.field.muzi.web.user.dto.resume.ResumeListResponse;
import com.field.muzi.web.user.dto.resume.ResumeSaveRequest;
import com.field.muzi.web.user.dto.resume.ResumeUpdateRequest;
import com.field.muzi.web.user.dto.visa.VisaCompleteRequest;
import com.field.muzi.web.user.dto.visa.VisaDetailsResponse;
import com.field.muzi.web.user.dto.visa.VisaSaveRequest;
import com.field.muzi.web.user.service.ResumeService;
import com.field.muzi.web.user.service.VisaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class VisaAPI {

    private final VisaService visaService;

    // 비자 자동채점 목록
//    @GetMapping("/visa")
//    public List<VisaDetailsResponse> visaList() {
//        return visaService.visaList();
//    }

    // 비자 자동채점 상세보기
    @GetMapping("/visa")
    public VisaDetailsResponse visaDetails() {
        return visaService.visaDetails();
    }

    // 비자 자동채점 신청하기
    @PostMapping("/visa")
    public void createVisa(@RequestBody VisaSaveRequest request) {
        visaService.createVisa(request);
    }

    // 비자 자동채점 설문 완료 제출
    @PutMapping("/visa")
    public void completeChecking(@RequestBody VisaCompleteRequest request) {
        visaService.completeChecking(request);
    }

}
