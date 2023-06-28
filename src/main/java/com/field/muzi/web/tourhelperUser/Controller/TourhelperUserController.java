package com.field.muzi.web.tourhelperUser.Controller;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.MemberInfoEntity;
import com.field.muzi.domain.visa.VisaEntity;
import com.field.muzi.domain.visa.VisaRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.common.dto.KakaoSignupRequest;
import com.field.muzi.web.common.dto.TokenResponse;
import com.field.muzi.web.common.service.KakaoLoginService;
import com.field.muzi.web.common.service.MemberInfoService;
import com.field.muzi.web.exception.business.CBusinessException;
import com.field.muzi.web.exception.business.CInvalidValueException;
import com.field.muzi.web.user.dto.member.MemberProfileResponse;
import com.field.muzi.web.user.dto.member.MemberUpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TourhelperUserController {

    private final VisaRepository visaRepository;
    private final MemberRepository memberRepository;
    private final KakaoLoginService kakaoLoginService;
    private final MemberInfoService memberInfoService;


    // 메인페이지
    @GetMapping()
    public String root() {
        return "/tourhelperUser/index";
    }
    
    // 메인페이지
//    @GetMapping("/index")
//    public void main(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/");
//    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "/tourhelperUser/join/join";
    }

    // 회원가입-추가정보 입력
    @GetMapping("/join/profile")
    public String inputProfile() {
        return "/tourhelperUser/join/profile";
    }

    // 회원가입-회원가입 완료
    @GetMapping("/join/complete")
    public String joinComplete() {
        return "/tourhelperUser/join/complete";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/index";
        }
        return "/tourhelperUser/login";
    }

    // 투어헬퍼 소개
    @GetMapping("/info")
    public String tourhelperInfo() {
        return "/tourhelperUser/info";
    }

    // E-7-4 자동배점 신청
    @GetMapping("/E74/apply")
    public String E74Apply(HttpServletResponse response) throws IOException {
        String memberSeq = SecurityContextHolder.getContext().getAuthentication().getName();
        if (memberSeq == null || memberSeq.equals("") || memberSeq.equals("anonymousUser")) {
            return "/error/user_login";
        } else {
            Optional<MemberEntity> member = memberRepository.findById(memberSeq);
            Optional<VisaEntity> visa = visaRepository.findByMember(member.get());
            if (visa.isPresent()) {
                response.sendRedirect("/E74/apply/complete");
            }
        }
        return "/tourhelperUser/E74/apply";
    }

    // E-7-4 자동배점 신청 완료
    @GetMapping("/E74/apply/complete")
    public String E74ApplyComplete() {
        return "/tourhelperUser/E74/complete";
    }

    // E-7-4 응답지 입력
    @GetMapping("/E74/apply/form")
    public String E74ApplyForm(HttpServletResponse response) throws IOException {
        String memberSeq = SecurityContextHolder.getContext().getAuthentication().getName();
        if (memberSeq == null || memberSeq.equals("") || memberSeq.equals("anonymousUser")) {
            response.sendRedirect("/E74/apply");
        } else {
            Optional<MemberEntity> member = memberRepository.findById(memberSeq);
            Optional<VisaEntity> visa = visaRepository.findByMember(member.get());
            if (visa.isPresent()) {
                if (visa.get().getAccept() != 1) {
                    return "/error/visa_authority";
                }
            }
        }
        return "/tourhelperUser/E74/form";
    }

    // 퇴직금 계산기
    @GetMapping("/calculator")
    public String calculator() {
        return "/tourhelperUser/calculator";
    }

    // 유학 정보
    @GetMapping("/abroad/info")
    public String abroadInfo() {
        return "/tourhelperUser/abroad/info";
    }

    // 유학 목록(추천대학)
    @GetMapping("/abroad/list")
    public String abroadList() {
        return "/tourhelperUser/abroad/list";
    }

    // 유학 상세정보
    @GetMapping("/abroad/detail")
    public String abroadDetail() {
        return "/tourhelperUser/abroad/detail";
    }

    // 유학 신청
    @GetMapping("/abroad/apply")
    public String abroadApply() {
        String memberSeq = SecurityContextHolder.getContext().getAuthentication().getName();
        if (memberSeq == null || memberSeq.equals("") || memberSeq.equals("anonymousUser")) {
            return "/error/user_login";
        }
        return "/tourhelperUser/abroad/apply";
    }

    // 유학 신청 완료
    @GetMapping("/abroad/apply/complete")
    public String abroadApplyComplete() {
        return "/tourhelperUser/abroad/complete";
    }

    // 상담 신청
    @GetMapping("/consult")
    public String consult() {
        return "/tourhelperUser/consult";
    }

    // 미얀마 여행
    @GetMapping("/myanmar/tour/list")
    public String myanmarTourList() {
        return "/tourhelperUser/travel/myanmar/list";
    }

    // 미얀마 여행 상세정보
    @GetMapping("/myanmar/tour/detail")
    public String myanmarTourDetail() {
        return "/tourhelperUser/travel/myanmar/detail";
    }

    // 국내 여행
    @GetMapping("/domestic/tour/list")
    public String domesticTourList() {
        return "/tourhelperUser/travel/domestic/list";
    }

    // 국내 여행 상세정보
    @GetMapping("/domestic/tour/detail")
    public String domesticTourDetail() {
        return "/tourhelperUser/travel/domestic/detail";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage() {
        return "/tourhelperUser/user/mypage";
    }

    // 개인정보(입력/수정)
    @GetMapping("/profile")
    public String profile() {
        return "/tourhelperUser/user/profile";
    }

    // 비즈니스 리스트
    @GetMapping("/business/list")
    public String businessList() {
        return "/tourhelperUser/business/list";
    }

    // 비즈니스 상세정보
    @GetMapping("/business/detail")
    public String businessdetail() {
        return "/tourhelperUser/business/detail";
    }


    // 개인정보보호
    @GetMapping("/infopravicy")
    public String infoPrivacy() {
        return "/tourhelperUser/infoPrivacy";
    }

    // 이용약관
    @GetMapping("/infoservice")
    public String infoService() {
        return "/tourhelperUser/infoService";
    }


    @GetMapping("/login/kakao")
    public void kakao_login(KakaoSignupRequest request, HttpServletResponse response) throws IOException {

        TokenResponse tokenResponse = kakaoLoginService.kakaoLogin(request);

        Optional<MemberEntity> optionalMember = memberRepository.findBySnsIdAndProvider(request.getSid(), request.getProvider());

        if(optionalMember.get().isWithdrawal()) {
            response.sendRedirect("/error/user-drop");
        }else {
            MemberInfoEntity memberInfo = optionalMember.get().getMemberInfo();

            if (memberInfo.getName() == null || memberInfo.getName().equals("")) {
                response.sendRedirect("/join/profile?memberSeq=" + optionalMember.get().getMemberSeq() + "&snsId=" + request.getSid());
            } else {
                Cookie access_cookie = new Cookie("AccessToken", tokenResponse.getAccessToken());
//        access_cookie.setMaxAge(60 * 60 * 24 );
                access_cookie.setMaxAge(-1);
                access_cookie.setPath("/");
                access_cookie.setHttpOnly(true);
                response.addCookie(access_cookie);

                Cookie refresh_cookie = new Cookie("RefreshToken", tokenResponse.getRefreshToken());
                refresh_cookie.setMaxAge(-1);
                refresh_cookie.setPath("/");
                refresh_cookie.setHttpOnly(true);
                response.addCookie(refresh_cookie);

                response.sendRedirect("/");
            }
        }
    }

    @ResponseBody
    @GetMapping("/api/common/login")
    public Map<String, Object> loginChk() {
        Map<String, Object> result = new HashMap<>();
        String memberSeq = SecurityContextHolder.getContext().getAuthentication().getName();

        if (memberSeq == null || memberSeq.equals("") || memberSeq.equals("anonymousUser")) {
            result.put("login", false);
        }else {
            MemberEntity member = EntityUtils.memberThrowable(memberRepository);
            result.put("login", true);
            result.put("profilePicture", member.getMemberInfo().getProfileImage());
            result.put("name", member.getMemberInfo().getName());
        }

        return  result;
    }

    @GetMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setValue(null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        response.sendRedirect("/");
    }

    @ResponseBody
    @PutMapping("/api/common/join")
    public TokenResponse completeJoin(@RequestBody MemberUpdateProfileRequest request, HttpServletResponse response) throws IOException {
        TokenResponse tokenResponse = memberInfoService.completeJoin(request);

        Cookie access_cookie = new Cookie("AccessToken", tokenResponse.getAccessToken());
//        access_cookie.setMaxAge(60 * 60 * 24 );
        access_cookie.setMaxAge(-1);
        access_cookie.setPath("/");
        access_cookie.setHttpOnly(true);
        response.addCookie(access_cookie);

        Cookie refresh_cookie = new Cookie("RefreshToken", tokenResponse.getRefreshToken());
        refresh_cookie.setMaxAge(-1);
        refresh_cookie.setPath("/");
        refresh_cookie.setHttpOnly(true);
        response.addCookie(refresh_cookie);

//        response.sendRedirect("/");

        return tokenResponse;
    }

    @ResponseBody
    @PutMapping("/api/user/member")
    public void updateProfile(@RequestBody MemberUpdateProfileRequest request) {
        memberInfoService.updateProfile(request);
    }

    @ResponseBody
    @GetMapping("/api/user/member")
    public MemberProfileResponse memberProfile() {
        return memberInfoService.memberProfile();
    }

}
