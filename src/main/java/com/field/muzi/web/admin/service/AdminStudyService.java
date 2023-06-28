package com.field.muzi.web.admin.service;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.admin.dto.course.CourseFirstLIstResponse;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.admin.dto.study.StudyStepUpdateRequest;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.user.dto.study.StudyDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminStudyService {

    private final StudyRepository studyRepository;
    private final FileHandler fileHandler;

    // 유학 신청 목록
    @Transactional
    public Page<StudyListResponseAdmin> studyList(Pageable pageable, String schoolSeq) {
        Page<StudyListResponseAdmin> result = studyRepository.studyList(pageable, schoolSeq);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (StudyListResponseAdmin response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }
        return result;
    }

    // 유학 신청 상세
    @Transactional
    public StudyDetailsResponse studyDetails(String studySeq) {
        StudyEntity foundStudy = EntityUtils.studyThrowable(studyRepository, studySeq);

        String file1Url = null;
        String file1 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile1())) {
            file1 = foundStudy.getFile1().getOriginName();
            file1Url = fileHandler.fileUrl(foundStudy.getFile1());
        }
        String file2Url = null;
        String file2 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile2())) {
            file2 = foundStudy.getFile2().getOriginName();
            file2Url = fileHandler.fileUrl(foundStudy.getFile2());
        }
        String file3Url = null;
        String file3 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile3())) {
            file3 = foundStudy.getFile3().getOriginName();
            file3Url = fileHandler.fileUrl(foundStudy.getFile3());
        }
        String file4Url = null;
        String file4 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile4())) {
            file4 = foundStudy.getFile4().getOriginName();
            file4Url = fileHandler.fileUrl(foundStudy.getFile4());
        }
        String file5Url = null;
        String file5 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile5())) {
            file5 = foundStudy.getFile5().getOriginName();
            file5Url = fileHandler.fileUrl(foundStudy.getFile5());
        }
        String file6Url = null;
        String file6 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile6())) {
            file6 = foundStudy.getFile6().getOriginName();
            file6Url = fileHandler.fileUrl(foundStudy.getFile6());
        }
        String file7Url = null;
        String file7 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile7())) {
            file7 = foundStudy.getFile7().getOriginName();
            file7Url = fileHandler.fileUrl(foundStudy.getFile7());
        }
        String file8Url = null;
        String file8 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile8())) {
            file8 = foundStudy.getFile8().getOriginName();
            file8Url = fileHandler.fileUrl(foundStudy.getFile8());
        }
        String file9Url = null;
        String file9 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile9())) {
            file9 = foundStudy.getFile9().getOriginName();
            file9Url = fileHandler.fileUrl(foundStudy.getFile9());
        }
        String file10Url = null;
        String file10 = null;
        if(!ObjectUtils.isEmpty(foundStudy.getFile10())) {
            file10 = foundStudy.getFile10().getOriginName();
            file10Url = fileHandler.fileUrl(foundStudy.getFile10());
        }

        return new StudyDetailsResponse(foundStudy, file1, file1Url, file2, file2Url, file3, file3Url, file4, file4Url, file5, file5Url,
                file6, file6Url, file7, file7Url, file8, file8Url, file9, file9Url, file10, file10Url);
    }

    // 사용자 단계 수정
    @Transactional
    public void updateStep(StudyStepUpdateRequest request) {
        StudyEntity foundStudy = EntityUtils.studyThrowable(studyRepository, request.getStudySeq());
        foundStudy.updateStep(request.getStep(), request.getDocument(), request.getActiveFileNumber(), request.getPassport(), request.getState());
    }
}
