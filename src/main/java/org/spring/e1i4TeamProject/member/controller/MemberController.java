package org.spring.e1i4TeamProject.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.spring.e1i4TeamProject.shop.service.CartService;
import org.spring.e1i4TeamProject.shop.service.CartShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final CartShopService cartShopService;
    private final CartService cartService;

    @GetMapping("/memberJoin")
    public String memberJoin(MemberDto memberDto, Model model) {

        model.addAttribute("memberDto", memberDto);

        return "member/memberJoin";
    }

    @PostMapping("/memberJoin")
    public String memberJoinOk(@Valid MemberDto memberDto,
                               BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "member/memberJoin";
        } else if (!memberDto.getUserPw().equals(memberDto.getUserPwCheck())) {
            bindingResult.rejectValue("userPwCheck", "passwordIncorrect",
                "두개의 비밀번호가 일치하지 않습니다.");
            return "member/memberJoin";
        } else {
            memberService.memberJoin(memberDto);
        }

        System.out.println(">>>>" + memberDto.getMemberAttachFile());
        return "redirect:/member/memberLogin";
    }

    @PostMapping("/sellerJoin")
    public String sellerJoinOk(@Valid MemberDto memberDto,
                               BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "member/memberJoin";
        } else {
            memberService.sellerJoin(memberDto);
        }

        System.out.println(">>>>" + memberDto.getMemberAttachFile());
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
                               Model model) {

//        List<CartShopListDto> cartShopListDto = cartShopService.cartList(myUserDetails.getMemberEntity().getId());
        MemberDto memberDto = memberService.memberDetail(id);

        if (myUserDetails != null) {
            model.addAttribute("myUserDetails", myUserDetails);
        }

//        if (cartShopListDto != null) {
//            model.addAttribute("cartShopListDto", cartShopListDto);
//        }
        model.addAttribute("memberDto", memberDto);

        return "member/memberDetail";
    }

    @PostMapping("/memberUpdate")
    public String memberUpdate(MemberDto memberDto) throws IOException {

        memberService.memberUpdate(memberDto);

        return "redirect:/member/memberDetail/" + memberDto.getId();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String memberDelete(@PathVariable("id") Long id) {
        memberService.memberDelete(id);

        String html = "<script>" +
            "alert('회원 탈퇴 성공');" +
            "location.href='/member/logout'" +
            "</script>";

        return html;
    }

    //    @ResponseBody
    @GetMapping("/memberCartList/{id}")
    public ResponseEntity<?> memberCartList(@PathVariable("id") Long id) {

        log.info("==============" + id + "=====memberId==");
        Map<String, List<CartShopListDto>> map = new HashMap<>();

        List<CartShopListDto> cartShopListDto = cartShopService.memberCartList(id);


        map.put("cartList", cartShopListDto);

        System.out.println(map + " map");
        log.info("==============" + map + "=======");

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
