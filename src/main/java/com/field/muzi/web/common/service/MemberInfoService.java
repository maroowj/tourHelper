package com.field.muzi.web.common.service;


import com.field.muzi.config.security.JwtProvider;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.MemberInfoEntity;
import com.field.muzi.domain.refreshtoken.RefreshToken;
import com.field.muzi.domain.refreshtoken.RefreshTokenRepository;
import com.field.muzi.domain.user.Role;
import com.field.muzi.repository.MemberInfoRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.member.MemberDetailsResponse;
import com.field.muzi.web.admin.dto.member.MemberListResponse;
import com.field.muzi.web.admin.dto.member.MemberWithdrawalRequest;
import com.field.muzi.web.common.dto.TokenResponse;
import com.field.muzi.web.exception.business.CEntityNotFoundException;
import com.field.muzi.web.user.dto.member.MemberProfileResponse;
import com.field.muzi.web.user.dto.member.MemberUpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse completeJoin(MemberUpdateProfileRequest request) {
        MemberEntity member = memberRepository.findByMemberSeqAndSnsId(request.getMemberSeq(), request.getSnsId()).orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);

        MemberInfoEntity memberInfo = member.getMemberInfo();
        memberInfo.updateProfile(
                request.getName(),
                request.getTel(),
                request.getPreference1(),
                request.getPreference2(),
                request.getCountry()
        );

        refreshTokenRepository.findByKey(member.getMemberSeq()).ifPresent(refreshTokenRepository::delete);
        TokenResponse tokenResponse = jwtProvider.createToken(member.getMemberSeq(), member.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        refreshTokenRepository.save(RefreshToken.create(member.getMemberSeq(), tokenResponse.getRefreshToken()));

        return tokenResponse;

    }

    @Transactional
    public void updateProfile(MemberUpdateProfileRequest request) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);

        MemberInfoEntity memberInfo = foundMember.getMemberInfo();
        memberInfo.updateProfile(
                request.getName(),
                request.getTel(),
                request.getPreference1(),
                request.getPreference2(),
                request.getCountry());
    }

    @Transactional
    public MemberProfileResponse memberProfile() {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository);
        MemberInfoEntity memberInfo = foundMember.getMemberInfo();
        return new MemberProfileResponse(memberInfo, foundMember);
    }

    @Transactional
    public Page<MemberListResponse> memberList(Pageable pageable, String keyword) {
        Page<MemberListResponse> result = memberRepository.memberList(pageable, keyword);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (MemberListResponse response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public MemberDetailsResponse memberDetails(String memberSeq) {
        MemberEntity foundMember = EntityUtils.memberThrowable(memberRepository, memberSeq);
        return new MemberDetailsResponse(foundMember);
    }

    @Transactional
    public void memberWithdrawal(MemberWithdrawalRequest request) {
        for(String memberSeq : request.getMemberSeqList()) {
            MemberEntity member= EntityUtils.memberThrowable(memberRepository, memberSeq);
            member.dropMember();
        }
    }
}
