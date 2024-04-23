package org.spring.e1i4TeamProject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberService memberService;
    @GetMapping({"/","/index"})
    public String index(MemberDto memberDto, Model model){

        memberService.uploadMemberImg(memberDto);

        model.addAttribute("memberDto", memberDto);

        return "index";
    }

}

