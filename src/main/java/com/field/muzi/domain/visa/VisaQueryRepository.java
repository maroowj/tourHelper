package com.field.muzi.domain.visa;


import com.field.muzi.web.admin.dto.visa.VisaListResponseAdmin;
import com.field.muzi.web.common.dto.CommonCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisaQueryRepository {

    Page<VisaListResponseAdmin> visaList(Pageable pageable, String keyword);
}
