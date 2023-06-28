package com.field.muzi.web.user.controller.API;

import com.field.muzi.web.user.dto.resume.ResumeListResponse;
import com.field.muzi.web.user.dto.resume.ResumeSaveRequest;
import com.field.muzi.web.user.dto.resume.ResumeUpdateRequest;
import com.field.muzi.web.user.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ResumeAPI {

    private final ResumeService resumeService;

    // 취업 이력 목록
    @GetMapping("/resume")
    public List<ResumeListResponse> responseList() {
        return resumeService.resumeList();
    }

    // 취업 이력 상세보기
    @GetMapping("/resume/{resumeSeq}")
    public ResumeListResponse resumeDetails(@PathVariable("resumeSeq") String resumeSeq) {
        return resumeService.resumeDetails(resumeSeq);
    }

    // 취업 이력 추가
    @PostMapping("/resume")
    public void createResume(@RequestBody ResumeSaveRequest request) {
        resumeService.createResume(request);
    }

    // 취업 이력 수정
    @PutMapping("/resume")
    public void updateResume(@RequestBody ResumeUpdateRequest request) {
        resumeService.updateResume(request);
    }

    // 취업 이력 삭제
    @DeleteMapping("/resume/{resumeSeq}")
    public void deleteResume(@PathVariable("resumeSeq") String resumeSeq) {
        resumeService.deleteResume(resumeSeq);
    }
}
