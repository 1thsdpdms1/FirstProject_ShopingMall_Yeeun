package org.spring.e1i4TeamProject.member.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberJoin")
    public String memberJoin(MemberDto memberDto, Model model){

        model.addAttribute("memberDto", memberDto);

        return "member/memberJoin";
    }

    @PostMapping("/memberJoin")
    public String memberJoinOk(@Valid MemberDto memberDto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/memberJoin";
        }
        else{
            memberService.memberJoin(memberDto);
        }
        return "redirect:/member/memberLogin";
    }

    @GetMapping("/memberLogin")
    public String memberLogin(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        MemberDto memberDto, Model model) {

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/memberLogin";
    }

    @GetMapping("/memberDetail/{id}")
    public String memberDetail(@PathVariable("id") Long id,
         @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
         Model model){

        MemberDto memberDto = memberService.memberDetail(id);

        if(myUserDetails != null){
            model.addAttribute("myUserDetails", myUserDetails);
        }
        model.addAttribute("memberDto", memberDto);

        return "member/memberDetail";
    }
}
