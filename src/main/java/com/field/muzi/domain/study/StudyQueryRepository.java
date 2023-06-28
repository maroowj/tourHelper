package com.field.muzi.domain.study;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.study.StudyListResponseAdmin;
import com.field.muzi.web.user.dto.form.FormListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyQueryRepository {

    Page<StudyListResponseAdmin> studyList(Pageable pageable, String schoolSeq);
}
