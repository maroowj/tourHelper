package com.field.muzi.domain.form;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.user.dto.form.FormListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormQueryRepository {
    Page<FormListResponse> formListByMember(Pageable pageable, MemberEntity member);
}
