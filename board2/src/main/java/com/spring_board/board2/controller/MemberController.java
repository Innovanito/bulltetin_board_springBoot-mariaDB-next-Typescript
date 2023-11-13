package com.spring_board.board2.controller;

import com.spring_board.board2.entity.Member;
import com.spring_board.board2.repository.MemberRepository;
import com.spring_board.board2.service.LoginService;
import com.spring_board.board2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MemberController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/login")
    public String login() {
        return "login2";
    }

    @GetMapping("/signup")
    public String signup() {
        return "createmember";
    }

    @PostMapping("/memberCreate")
    public String memberCreate(Member member,Model model) {
        String memberToken = loginService.createToken(member.getMemberId(), 36*1000);

        String password1 = member.getPassword();
        String password2Confirm = member.getLoginPwConfirm();

        if (password1.equals(password2Confirm)){
            member.setTokenValue(memberToken);
            memberService.createNewMember(member);

            model.addAttribute("message", "회원생성이 완료되었습니다.");
            model.addAttribute("searchUrl", "/login");
        } else {
            model.addAttribute("message", "비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            model.addAttribute("searchUrl", "/signup");
        }
        return "message";

    }

    @PostMapping("/LoginProcess")
    public String loginProcedure(Member member, Model model, HttpServletResponse response) {

        String inputId = member.getMemberId();
        String inputPassword = member.getPassword();

        String tokenValue = memberService.getTokenValueIfIdAndPasswordExist(inputId, inputPassword);

        if (tokenValue != null) {
            model.addAttribute("message", "로그인 성공했습니다.");
            model.addAttribute("searchUrl", "/board/list");

            // JwtToken 값을 쿠키에 추가
            Cookie tokenCookie = new Cookie("jwtToken", tokenValue);
            tokenCookie.setMaxAge(60 * 60); // 토큰 유효 기간 설정 (예: 1시간)
            tokenCookie.setPath("/"); // 쿠키 경로 설정
            response.addCookie(tokenCookie);
        } else {
            model.addAttribute("message", "로그인 실패했습니다.");
            model.addAttribute("searchUrl", "/login");
        }
        return "message";
    }

}