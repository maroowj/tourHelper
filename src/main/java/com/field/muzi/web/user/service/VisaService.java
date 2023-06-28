package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.visa.VisaEntity;
import com.field.muzi.domain.visa.VisaRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.exception.business.CEntityNotFoundException;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.user.dto.visa.VisaCompleteRequest;
import com.field.muzi.web.user.dto.visa.VisaDetailsResponse;
import com.field.muzi.web.user.dto.visa.VisaSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VisaService {

    private final MemberRepository memberRepository;
    private final VisaRepository visaRepository;

    // 자동배점 신청
    @Transactional
    public void createVisa(VisaSaveRequest request) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        if(visaRepository.existsAllByMember(foundMember)) {
            throw new CInvalidValueException.CAlreadyVisaException();
        }

        visaRepository.save(
                VisaEntity.create(
                        foundMember,
                        request.getName(),
                        request.getPhone(),
                        request.getCountry(),
                        request.getAddress1(),
                        request.getAddress2(),
                        request.getPostCode(),
                        request.getCompany(),
                        request.getJoinDate()
                )
        );
    }

    // 자동 배점 문항 완료
    @Transactional
    public void completeChecking(VisaCompleteRequest request) {
        VisaEntity foundVisa = EntityUtils.visaThrowable(visaRepository, request.getVisaSeq());
        foundVisa.complete(request.getContent(), request.getPoint());
    }

    // 비자 자동배점 신청 목록
    @Transactional
    public List<VisaDetailsResponse> visaList() {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        List<VisaDetailsResponse> responseList = new ArrayList<>();
        List<VisaEntity> visaEntities = visaRepository.findAllByMemberOrderByVisaSeqDesc(member);

        for(VisaEntity visa : visaEntities) {
            VisaDetailsResponse response = new VisaDetailsResponse(visa);
            responseList.add(response);
        }
        return responseList;
    }

    // 비자 자동배점 신청 상새 내역
    @Transactional
    public VisaDetailsResponse visaDetails() {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository);
        VisaEntity foundVisa = visaRepository.findByMember(member).orElseThrow(CEntityNotFoundException.CVisaNotFoundException::new);

        return new VisaDetailsResponse(foundVisa);
    }
}
