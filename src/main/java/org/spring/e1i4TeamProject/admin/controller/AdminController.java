package org.spring.e1i4TeamProject.admin.controller;


import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.admin.service.AdminService;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.service.BoardService;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    //회원리스트
    @GetMapping("/memberList")
    public String memberList(@PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                             Model model,
                             @RequestParam(name = "subject", required = false) String subject,
                             @RequestParam(name = "search", required = false) String search) {

        Page<MemberDto> memberList = adminService.memberList(pageable, subject, search);

        int totalPage = memberList.getTotalPages();//전체page
        int newPage = memberList.getNumber();//현재page
        Long totalElements = memberList.getTotalElements();//전체 레코드 갯수
        int size = memberList.getSize();//페이지당 보이는 갯수

        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("memberList", memberList);

        return "admin/memberList";
    }

    //선생님리스트
    @GetMapping("/sellerList")
    public String sellerList(@PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                             Model model,
                             @RequestParam(name = "subject", required = false) String subject,
                             @RequestParam(name = "search", required = false) String search) {


        Page<MemberDto> sellerList = adminService.sellerList(pageable, subject, search);


        int totalPage = sellerList.getTotalPages();//전체page
        int newPage = sellerList.getNumber();//현재page
        Long totalElements = sellerList.getTotalElements();//전체 레코드 갯수
        int size = sellerList.getSize();//페이지당 보이는 갯수

        int blockNum = 3; //브라우저에 보이는 페이지 갯수

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPage
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPage);
        int endPage = (startPage + blockNum) - 1 < totalPage ? (startPage + blockNum) - 1 : totalPage;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sellerList", sellerList);

        return "admin/sellerList";
    }

    @GetMapping("/memberListDelete/{id}")
    public String delete(@PathVariable("id") Long id) {
        adminService.memberListDelete(id);
        return "redirect:/admin/memberList";
    }


}