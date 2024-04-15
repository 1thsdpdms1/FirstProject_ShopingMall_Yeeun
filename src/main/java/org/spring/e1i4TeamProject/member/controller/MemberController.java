package org.spring.e1i4TeamProject.member.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

}
