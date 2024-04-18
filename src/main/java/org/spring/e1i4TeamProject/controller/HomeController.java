package org.spring.e1i4TeamProject.controller;

import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(MemberDto memberDto, Model model){

        model.addAttribute("memberDto", memberDto);

        return "index";
    }

}

