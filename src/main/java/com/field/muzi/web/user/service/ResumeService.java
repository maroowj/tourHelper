package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.resume.ResumeEntity;
import com.field.muzi.domain.resume.ResumeRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.user.dto.resume.ResumeListResponse;
import com.field.muzi.web.user.dto.resume.ResumeSaveRequest;
import com.field.muzi.web.user.dto.resume.ResumeUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResumeService {

    private final MemberRepository memberRepository;
    private final ResumeRepository resumeRepository;

    // 취업 이력 등록
    @Transactional
    public void createResume(ResumeSaveRequest request) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);

        resumeRepository.save(
                ResumeEntity.create(
                        foundMember,
                        request.getCompany(),
                        request.getJoinDate(),
                        request.getOutDate(),
                        request.getSalary(),
                        request.getWorking()
                )
        );
    }

    // 취업 이력 수정
    @Transactional
    public void updateResume(ResumeUpdateRequest request) {
        ResumeEntity foundResume = EntityUtils.resumeThrowable(resumeRepository, request.getResumeSeq());

        foundResume.update(
                request.getCompany(),
                request.getJoinDate(),
                request.getOutDate(),
                request.getSalary(),
                request.getWorking()
        );
    }

    // 취업 이력 삭제
    @Transactional
    public void deleteResume(String resumeSeq) {
        ResumeEntity foundResume = EntityUtils.resumeThrowable(resumeRepository, resumeSeq);
        resumeRepository.delete(foundResume);
    }

    // 취업 이력 목록
    @Transactional
    public List<ResumeListResponse> resumeList() {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        List<ResumeListResponse> resumeList = new ArrayList<>();
        List<ResumeEntity> resumeEntities = resumeRepository.findAllByMemberOrderByResumeSeqDesc(foundMember);

        for(ResumeEntity resume : resumeEntities) {
            Date outDate = resume.getOutDate();
            if (resume.getOutDate() == null) {
                outDate = new Date();
            }
            int diffSec = (int) ((outDate.getTime() - resume.getJoinDate().getTime()) / 1000);
            int workDays = diffSec / (60 * 60 * 24);
            int workYears = workDays / 365;

            ResumeListResponse response = new ResumeListResponse(resume, workDays, workYears);
            resumeList.add(response);
        }
        return resumeList;
    }

    // 취업 이력 상세
    @Transactional
    public ResumeListResponse resumeDetails(String resumeSeq) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        ResumeEntity foundResume = EntityUtils.resumeThrowable(resumeRepository, resumeSeq);

        if(foundResume.getMember()!=foundMember) {
            throw new CInvalidValueException.CResumeInvalidUserException();
        }

        Date outDate = foundResume.getOutDate();
        if (foundResume.getOutDate() == null) {
            outDate = new Date();
        }
        int diffSec = (int) ((outDate.getTime() - foundResume.getJoinDate().getTime()) / 1000);
        int workDays = diffSec / (60 * 60 * 24);
        int workYears = workDays / 365;

        return new ResumeListResponse(foundResume, workDays, workYears);
    }
}
