package com.field.muzi.web.admin.service;

import com.field.muzi.domain.visa.VisaEntity;
import com.field.muzi.domain.visa.VisaRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.visa.VisaListResponseAdmin;
import com.field.muzi.web.admin.dto.visa.VisaMemoRequest;
import com.field.muzi.web.common.dto.CommonCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminVisaService {

    private final VisaRepository visaRepository;

    @Transactional
    public Page<VisaListResponseAdmin> visaListPage(Pageable pageable, String keyword) {
        Page<VisaListResponseAdmin> result = visaRepository.visaList(pageable, keyword);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (VisaListResponseAdmin response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public VisaListResponseAdmin visaDetails(String visaSeq) {
        VisaEntity visa = EntityUtils.visaThrowable(visaRepository, visaSeq);
        return new VisaListResponseAdmin(visa);
    }

    @Transactional
    public void visaAccept(String visaSeq) {
        VisaEntity visa = EntityUtils.visaThrowable(visaRepository, visaSeq);
        visa.accept();
    }

    @Transactional
    public void visaComment(String visaSeq, VisaMemoRequest request) {
        VisaEntity visa = EntityUtils.visaThrowable(visaRepository, visaSeq);
        visa.updateComment(request.getComment());
    }
}
