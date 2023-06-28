package com.field.muzi.web.user.service;

import com.field.muzi.domain.form.FormEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.form.FormRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.user.dto.form.FormDetailsResponse;
import com.field.muzi.web.user.dto.form.FormListResponse;
import com.field.muzi.web.user.dto.form.FormSaveRequest;
import com.field.muzi.web.user.dto.form.FormUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
@RequiredArgsConstructor
public class FormService {

    private final MemberRepository memberRepository;
    private final FormRepository formRepository;

    @Transactional
    public void createForm(FormSaveRequest request) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        formRepository.save(
                FormEntity.create(
                        member,
                        request.getTitle(),
                        request.getCategory(),
                        request.getContent(),
                        request.getEtc()
                )
        );
    }

    @Transactional
    public void updateForm(FormUpdateRequest request, String formSeq) {
        FormEntity form  = EntityUtils.formThrowable(formRepository, formSeq);
        form.update(request.getTitle(), request.getContent(), request.getEtc());
    }

    @Transactional
    public Page<FormListResponse> formList(Pageable pageable) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        return formRepository.formListByMember(pageable, member);
    }

    @Transactional
    public FormDetailsResponse formDetails(String formSeq) {
        FormEntity form = EntityUtils.formThrowable(formRepository, formSeq);
        return new FormDetailsResponse(form);
    }

    @Transactional
    public void deleteForm(String formSeq) {
        FormEntity form  = EntityUtils.formThrowable(formRepository, formSeq);
        form.delete();
    }
}
