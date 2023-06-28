package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.visa.VisaListResponseAdmin;
import com.field.muzi.web.admin.dto.visa.VisaMemoRequest;
import com.field.muzi.web.admin.service.AdminVisaService;
import com.field.muzi.web.common.dto.CommonCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminVisaAPI {

    private final AdminVisaService adminVisaService;

    @GetMapping("/visa")
    public Page<VisaListResponseAdmin> visaListPage(@PageableDefault(sort = "visaSeq", direction = Sort.Direction.DESC) Pageable pageable, String keyword) {
        return adminVisaService.visaListPage(pageable, keyword);
    }

    @GetMapping("/visa/{visaSeq}")
    public VisaListResponseAdmin visaDetails(@PathVariable("visaSeq") String visaSeq) {
        return adminVisaService.visaDetails(visaSeq);
    }

    @PutMapping("/visa/{visaSeq}")
    public void visaAccept(@PathVariable("visaSeq") String visaSeq) {
        adminVisaService.visaAccept(visaSeq);
    }

    @PutMapping("/visa/memo/{visaSeq}")
    public void memo(@PathVariable("visaSeq") String visaSeq, @RequestBody VisaMemoRequest request) {
        adminVisaService.visaComment(visaSeq, request);
    }
}
