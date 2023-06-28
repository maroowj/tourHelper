package com.field.muzi.web.admin.controller.API;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.member.MemberDetailsResponse;
import com.field.muzi.web.admin.dto.member.MemberListResponse;
import com.field.muzi.web.admin.dto.member.MemberWithdrawalRequest;
import com.field.muzi.web.common.service.MemberInfoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberInfoService memberInfoService;

    @GetMapping("/member")
    public Page<MemberListResponse> memberList(@PageableDefault(sort = "memberSeq", direction = Sort.Direction.DESC) Pageable pageable, String keyword) {
        return memberInfoService.memberList(pageable, keyword);
    }

    @GetMapping("/member/{memberSeq}")
    public MemberDetailsResponse memberDetails(@PathVariable("memberSeq") String memberSeq) {
        return memberInfoService.memberDetails(memberSeq);
    }

    @DeleteMapping("/member")
    public void memberWithdrawal(@RequestBody MemberWithdrawalRequest request) {
        memberInfoService.memberWithdrawal(request);
    }
}
