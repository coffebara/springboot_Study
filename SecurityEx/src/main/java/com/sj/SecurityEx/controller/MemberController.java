package com.sj.SecurityEx.controller;

import com.sj.SecurityEx.dto.MemberDto;
import com.sj.SecurityEx.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @GetMapping("/member/join")
    public String createForm(Model model){
//        model.addAttribute("memberDto", String email, String name, String password, boolean fromSocial);
        return "CreateMemberForm";
    }

    @PostMapping("/member/join")
    public String create(MemberDto memberDto) {

        try {
            memberService.save(memberDto);
        } catch (Exception e) {
            e.printStackTrace();

            return "CreateMemberForm";
        }

        return "redirect:/";
    }
}
