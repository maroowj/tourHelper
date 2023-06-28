package com.field.muzi.web.user.controller;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.MemberInfoEntity;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.web.common.dto.KakaoSignupRequest;
import com.field.muzi.web.common.dto.TokenResponse;
import com.field.muzi.web.common.service.KakaoLoginService;
import com.field.muzi.web.common.service.MemberInfoService;
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
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/school")
public class UserController {

    private final KakaoLoginService kakaoLoginService;
    private final MemberRepository memberRepository;
    private final MemberInfoService memberInfoService;

    // 메인페이지
    @GetMapping
    public String main() {
        return "/user/index";
    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "";
    }

    // 추가정보 입력
    @GetMapping("/join/info")
    public String joinInfo() {
        return "";
    }

    // 로그인
    @GetMapping("/login")
    public String login_view() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "/social/login";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage() {
        return "";
    }

    // 마이페이지 - 개인 정보 수정
    @GetMapping("/mypage/info")
    public String myInfo() {
        return "";
    }

    // 회사 소개
    @GetMapping("/introduce")
    public String intro() {
        return "/user/topMenu/intro";
    }

    // VR투어 박물관
    @GetMapping("/vr/museum")
    public String vr_museum() {
        return "/user/topMenu/vr/museum";
    }

    // VR투어 호텔
    @GetMapping("/vr/hotel")
    public String vr_hotel() {
        return "/user/topMenu/vr/hotel";
    }

    // VR투어 카페
    @GetMapping("/vr/cafe")
    public String vr_cafe() {
        return "/user/topMenu/vr/cafe";
    }

    // VR투어 기타
    @GetMapping("/vr/etc")
    public String vr_etc() {
        return "/user/topMenu/vr/etc";
    }

    // VR투어 제작가격
    @GetMapping("/vr/production-price")
    public String vr_productionPrice() {
        return "/user/topMenu/vr/productionPrice";
    }

    // VR투어 제작의뢰
    @GetMapping("/vr/production-request")
    public String vr_productionRequest() {
        return "/user/topMenu/vr/productionRequest";
    }

    // 여행자 보험
    @GetMapping("/insurance")
    public String insurance() {
        return "/user/topMenu/insurance";
    }

	/*
	 // 1차 견학시설 상세페이지
	 @GetMapping("/first-course")
	 public String firstCourse() {
	 return "/user/course/firstCourse";
	 }

	 // 2차 견학시설 상세페이지
	 @GetMapping("/second-course")
	 public String secondCourse() {
	 return "/user/course/secondCourse";
	 }

	 // 예약 확인 페이지
	 @GetMapping("/reservation")
	 public String reservation() {
	 return "/user/reservation/reservation";
	 }
	 */

	/*
	@GetMapping("/login")
	public String loginPage() {
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return "redirect:/user/campaign/stats";
		}
		return "/user/login";
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request,
											   HttpServletRequest req, HttpServletResponse response) {
		List<Role> role = new ArrayList<>();
		role.add(Role.ROLE_USER);

		TokenDto tokenDto = authService.login(request, role);

		Cookie access_cookie = new Cookie("AccessToken", tokenDto.getAccessToken());
		access_cookie.setMaxAge(60 * 30); // 30분
//		access_cookie.setMaxAge(60 * 60 * 24);
		access_cookie.setPath("/");
		//access_cookie.setSecure(true);
		access_cookie.setHttpOnly(true);

		response.addCookie(access_cookie);

		if (request.isLoginSave()) {
			Cookie loginSaveCookie = new Cookie("savedId", request.getMemberId());
			loginSaveCookie.setMaxAge(60 * 60 * 24 * 14); // 2주
			loginSaveCookie.setPath("/");
			response.addCookie(loginSaveCookie);
		} else {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("savedId")) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						break;
					}
				}
			}
		}
		return ResponseEntity.ok(tokenDto);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {


		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (!cookie.getName().equals("savedId")) {
				cookie.setValue(null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

		return "redirect:/user/login";
	}

	@GetMapping("/force/logout")
	public String forceLogout(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (!cookie.getName().equals("savedId")) {
				cookie.setValue(null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

		return "redirect:/user/login";
	}
	 */


//	/* 캠페인 진행상황 */
//	// 캠페인 목록
//	@GetMapping("/campaign/stats")
//	public String campaignStats() {
//		return "/user/campaign/stats";
//	}
//
//	/* 전화홍보 진행상황 */
//	// 전화홍보 목록
//	@GetMapping("/staff/stats")
//	public String staffStats() {
//		return "/user/staff/stats";
//	}
//
//	/* 통화이력 */
//	// 통화이력
//	@GetMapping("/stats/log")
//	public String statsLog() {
//		return "/user/stats/log";
//	}
//
//	/* 사용자설정 */
//	// 사용자설정
//	@GetMapping("/profile")
//	public String profile() {
//		return "/user/profile";
//	}
//
//
//	@GetMapping("/reservation/go")
//	public String reservationGo(String dblistMatchSeq, RedirectAttributes rttr) {
//		rttr.addAttribute("dblistMatchSeq", dblistMatchSeq);
//
//		return "redirect:/user/staff/stats";
//	}
}
