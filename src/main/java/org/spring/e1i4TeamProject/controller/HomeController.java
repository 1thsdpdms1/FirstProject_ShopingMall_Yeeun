package org.spring.e1i4TeamProject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.service.BoardService;
<<<<<<< HEAD
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.service.MemberService;
=======
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.dto.MemberDto;
import org.spring.e1i4TeamProject.member.service.MemberService;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.service.ShopLikeService;
import org.spring.e1i4TeamProject.shop.service.ShopService;
>>>>>>> dev
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
<<<<<<< HEAD
=======
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
>>>>>>> dev
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberService memberService;
<<<<<<< HEAD

    private final BoardService boardService;

//    @GetMapping({"/","/index"})
//    public String index(MemberDto memberDto, Model model){
//
//        model.addAttribute("memberDto", memberDto);
//
//        return "index";
//    }

    @GetMapping({"/","/index"})
    public String index(MemberDto memberDto, BoardDto boardDto,Model model)
    {
=======
>>>>>>> dev

    private final BoardService boardService;

    private final ShopService shopService;


    @GetMapping({"/","/index"})
    public String index(MemberDto memberDto, BoardDto boardDto,Model model)
    {

        List<ShopDto> liked = shopService.liked();
        model.addAttribute("memberDto", memberDto);

        List<BoardDto> boardDtoList = boardService.topReviewBoardList(boardDto);

        model.addAttribute("boardDtoList", boardDtoList);
<<<<<<< HEAD
=======
        model.addAttribute("liked", liked);
>>>>>>> dev


        return "index";
    }


}

