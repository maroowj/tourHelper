package com.field.muzi.utils;

import com.field.muzi.domain.board.BoardEntity;
import com.field.muzi.domain.board.BoardRepository;
import com.field.muzi.domain.entity.*;
import com.field.muzi.domain.form.FormEntity;
import com.field.muzi.domain.form.FormRepository;
import com.field.muzi.domain.resume.ResumeEntity;
import com.field.muzi.domain.resume.ResumeRepository;
import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyRepository;
import com.field.muzi.domain.trip.TripEntity;
import com.field.muzi.domain.trip.TripRepository;
import com.field.muzi.domain.visa.VisaEntity;
import com.field.muzi.domain.visa.VisaRepository;
import com.field.muzi.repository.*;

import com.field.muzi.web.exception.business.CEntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

public class EntityUtils {

    public static MemberEntity memberThrowable(MemberRepository memberRepository) {
        return memberRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }
    public static MemberEntity memberThrowable(MemberRepository memberRepository, String seq) {
        return memberRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }

    public static MemberEntity memberThrowableByMemberId(MemberRepository memberRepository, String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }

    public static DiyEntity diyThrowable(DiyRepository diyRepository, String seq) {
        return diyRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CDiyNotFoundException::new);
    }

    public static DiyDetailsEntity diyDetailsThrowable(DiyDetailsRepository diyDetailsRepository, String seq) {
        return diyDetailsRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CDiyDetailsNotFoundException::new);
    }

    public static DiyOptionsEntity diyOptionsThrowable(DiyOptionsRepository diyOptionsRepository, String seq) {
        return diyOptionsRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CDiyOptionsNotFoundException::new);
    }

    public static FormEntity formThrowable(FormRepository formRepository, String seq) {
        return formRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CFormNotFoundException::new);
    }

    public static FirstCourseEntity schoolThrowable(FirstCourseRepository firstCourseRepository, String seq) {
        return firstCourseRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CSchoolNotFoundException::new);
    }

    public static StudyEntity studyThrowable(StudyRepository studyRepository, String seq) {
        return studyRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CStudyNotFoundException::new);
    }

    public static ResumeEntity resumeThrowable(ResumeRepository resumeRepository, String seq) {
        return resumeRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CResumeNotFoundException::new);
    }

    public static VisaEntity visaThrowable(VisaRepository visaRepository, String seq) {
        return visaRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CVisaNotFoundException::new);
    }
    public static SecondCourseEntity tourThrowable(SecondCourseRepository secondCourseRepository, String seq) {
        return secondCourseRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CTourNotFoundException::new);
    }
    public static BoardEntity boardThrowable(BoardRepository boardRepository, String seq) {
        return boardRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CBoardNotFoundException::new);
    }
    public static TripEntity tripThrowable(TripRepository tripRepository, String seq) {
        return tripRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CTripNotFoundException::new);
    }

}
