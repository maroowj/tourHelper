package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.study.StudyEntity;
import com.field.muzi.domain.study.StudyRepository;
import com.field.muzi.repository.FirstCourseRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.repository.setup.FileManagerRepository;
import com.field.muzi.setup.FileManagerEntity;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.utils.handler.FileHandler;
import com.field.muzi.web.exception.business.CEntityNotFoundException;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.exception.io.CIOException;
import com.field.muzi.web.user.dto.study.StudyDetailsResponse;
import com.field.muzi.web.user.dto.study.StudyFileUploadRequest;
import com.field.muzi.web.user.dto.study.StudyListResponse;
import com.field.muzi.web.user.dto.study.StudySaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final FirstCourseRepository firstCourseRepository;
    private final FileHandler fileHandler;
    private final FileManagerRepository fileManagerRepository;

    // 유학 신청
    @Transactional
    public void createStudy(StudySaveRequest request) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        FirstCourseEntity foundSchool = EntityUtils.schoolThrowable(firstCourseRepository, request.getSchoolSeq());

        studyRepository.save(
                StudyEntity.create(
                        foundMember,
                        foundSchool,
                        request.getPeriod(),
                        request.getTopik(),
                        request.getName(),
                        request.getGender(),
                        request.getPhone(),
                        request.getCountry(),
                        request.getEtc(),
                        request.getBirth()
                )
        );
    }

    // 유학 신청 취소
    @Transactional
    public void deleteStudy(String studySeq) {
        StudyEntity foundStudy = EntityUtils.studyThrowable(studyRepository, studySeq);
        studyRepository.delete(foundStudy);
    }

    // 유학 신청 목록 불러오기
    @Transactional
    public List<StudyListResponse> studyList() {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        List<StudyEntity> studyList = studyRepository.findAllByMember(foundMember);
        List<StudyListResponse> responseList = new ArrayList<>();

        if(studyList!=null && studyList.size()!=0) {
            for(StudyEntity study : studyList) {
                StudyListResponse response = new StudyListResponse(study);
                responseList.add(response);
            }
        }

        return responseList;
    }

    // 유학 상세 내용 불러오기
    @Transactional
    public StudyDetailsResponse studyDetails(String studySeq) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        StudyEntity foundStudy = EntityUtils.studyThrowable(studyRepository, studySeq);
        if(foundStudy.getMember()!=foundMember) {
            throw new CInvalidValueException.CStudyInvalidUserException();
        }

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

    // 파일 업로드
    @Transactional
    public void fileUpload(StudyFileUploadRequest request) {
        StudyEntity foundStudy = EntityUtils.studyThrowable(studyRepository, request.getStudySeq());
        
        if (request.getFile1()!=null && !request.getFile1().isEmpty()) {
            FileManagerEntity file1 = buildFileManagerEntity(request.getFile1());
            foundStudy.uploadFile1(file1);
        }
        if (request.getFile2()!=null && !request.getFile2().isEmpty()) {
            FileManagerEntity file2 = buildFileManagerEntity(request.getFile2());
            foundStudy.uploadFile2(file2);
        }
        if (request.getFile3()!=null && !request.getFile3().isEmpty()) {
            FileManagerEntity file3 = buildFileManagerEntity(request.getFile3());
            foundStudy.uploadFile3(file3);
        }
        if (request.getFile4()!=null && !request.getFile4().isEmpty()) {
            FileManagerEntity file4 = buildFileManagerEntity(request.getFile4());
            foundStudy.uploadFile4(file4);
        }
        if (request.getFile5()!=null && !request.getFile5().isEmpty()) {
            FileManagerEntity file5 = buildFileManagerEntity(request.getFile5());
            foundStudy.uploadFile5(file5);
        }
        if (request.getFile6()!=null && !request.getFile6().isEmpty()) {
            FileManagerEntity file6 = buildFileManagerEntity(request.getFile6());
            foundStudy.uploadFile6(file6);
        }
        if (request.getFile7()!=null && !request.getFile7().isEmpty()) {
            FileManagerEntity file7 = buildFileManagerEntity(request.getFile7());
            foundStudy.uploadFile7(file7);
        }
        if (request.getFile8()!=null && !request.getFile8().isEmpty()) {
            FileManagerEntity file8 = buildFileManagerEntity(request.getFile8());
            foundStudy.uploadFile8(file8);
        }
        if (request.getFile9()!=null && !request.getFile9().isEmpty()) {
            FileManagerEntity file9 = buildFileManagerEntity(request.getFile9());
            foundStudy.uploadFile9(file9);
        }
        if (request.getFile10()!=null && !request.getFile10().isEmpty()) {
            FileManagerEntity file10 = buildFileManagerEntity(request.getFile10());
            foundStudy.uploadFile10(file10);
        }
        
    }

    private FileManagerEntity buildFileManagerEntity(MultipartFile file) {
        FileManagerEntity fileManager = null;
        List<MultipartFile> imgs = new ArrayList<>();
        imgs.add(file);        
        try {
            fileManager = fileManagerRepository.save(fileHandler.parse(imgs, "study_file").get(0));
        } catch (Exception e) {
            throw new CIOException.CCloudCommunicationException();
        }
        return fileManager;
    }
}
