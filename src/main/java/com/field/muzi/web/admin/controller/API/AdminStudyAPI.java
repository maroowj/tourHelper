package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.admin.dto.study.StudyStepUpdateRequest;
import com.field.muzi.web.admin.service.AdminStudyService;
import com.field.muzi.web.user.dto.study.StudyDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminStudyAPI {

    private final AdminStudyService studyService;

    @GetMapping("/study")
    public Page<StudyListResponseAdmin> studyList(@PageableDefault(sort = "studySeq", direction = Sort.Direction.DESC) Pageable pageable, String schoolSeq) {
        return studyService.studyList(pageable, schoolSeq);
    }

    @GetMapping("/study/{studySeq}")
    public StudyDetailsResponse studyDetails(@PathVariable("studySeq") String studySeq) {
        return studyService.studyDetails(studySeq);
    }

    @PutMapping("/study/step")
    public void updateStep(@RequestBody StudyStepUpdateRequest request) {
        studyService.updateStep(request);
    }
}
