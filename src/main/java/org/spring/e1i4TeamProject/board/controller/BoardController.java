package org.spring.e1i4TeamProject.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.service.BoardReplyService;
import org.spring.e1i4TeamProject.board.service.BoardService;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final BoardReplyService boardReplyService;


    @GetMapping("/boardWrite")
    public String boardWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                             BoardDto boardDto, Model model) {

        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());

        return "board/boardWrite";
    }


    @PostMapping("/boardWrite")
    public String boardWriteOK(@Valid BoardDto boardDto,
                               BindingResult bindingResult, Model model) throws IOException {

//        boardService.boardInsertFile(boardDto);
        model.addAttribute("boardDto", boardDto);
        boardService.boardInsert(boardDto);

        return "redirect:/board/boardList";
        //글 작성후에 boardList 페이지로 이동
    }


    @GetMapping("/boardList")
    public String boardList(Model model, @AuthenticationPrincipal MyUserDetailsImpl myUserDetails) {
        List<BoardDto> boardDtoList = boardService.boardList();

        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("boardDtoList", boardDtoList);

        return "board/boardList";
    }

    @GetMapping("/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable("id") Long id) {

//        boardService.updateHit(id);

        //조회 -> BoardEntity id -> 파일이 있을 경우 FileEntity newFileName
        BoardDto board = boardService.boardDetail(id);

        // "board" 라는 이름으로 조회한 게시글(파일 있으면 파일 포함)을 저장
        // --> board/detail.html


        //게시글이 존재하면 -> 게시글에 연결된 덧글리스트
        List<BoardReplyDto> replyList = boardReplyService.boardReplyList(board.getId());

        model.addAttribute("board", board);
        model.addAttribute("replyList", replyList);

        return "board/boardDetail";
    }



/////////////////////////////////////////////////////////////
    //작성 페이지
    @PostMapping("/boardInquiry")
    public String Inquiry(BoardDto boardDto, @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                          Model model) throws IOException {
        boardService.boardInsertFile(boardDto);
        return "redirect:/board/boardList"; // 글 작성 후 글 목록 페이지로 리다이렉트
    }


    @GetMapping( "/boardInquiry/member/{id}/category/{category}")
    public String inquiry(@PathVariable("id") Long id,
                          @PathVariable("category") Long category,Model model) {
        List<BoardDto> boardList
           =boardService.boardMemberCategoryList(id, category);
       model.addAttribute("boardList",boardList);

        return "board/boardInquiry";
    }









    //작성 post
    @PostMapping("/inquiry/write")
    public ResponseEntity<?>  inquiryQ(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails, BoardDto boardDto)
     {
         boardDto.setBoardAttachFile(0);
         boardDto.setMemberEntity(MemberEntity.builder().id(myUserDetails.getMemberEntity().getId()).build());

         BoardDto board=boardService.boardMemberCategorySave(boardDto);
         Map<String,BoardDto> map=new HashMap<>();
         map.put("board",board);

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }



    @GetMapping("/inquiry/inquiryList")
    public ResponseEntity<?> shopList(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @AuthenticationPrincipal MyUserDetailsImpl myUserDetails, Model model) {

        Map<String,Object> pagingList=new HashMap<>();

        Page<BoardDto> inquiryList = boardService.inquirySearchPagingList(pageable);
        model.addAttribute("myUserDetails", myUserDetails);

        int totalPage = inquiryList.getTotalPages();
        int newPage = inquiryList.getNumber();
        int totalPages = inquiryList.getTotalPages();
        int blockNum = 5;

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
            ? (Math.floor(newPage / blockNum) * blockNum) + 1 : totalPages);

        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

/*        model.addAttribute("shopList", shopList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);*/

        pagingList.put("inquiryList",inquiryList);
        pagingList.put("startPage",startPage);
        pagingList.put("endPage",endPage);
//        pagingList.put("myUserDetails",myUserDetails);

        return ResponseEntity.status(HttpStatus.OK).body(pagingList);
    }

    @GetMapping("/boardAnswer")
    public String boardAnswer(@RequestParam("id")Long id,Model model){
        BoardDto boardDto = boardService.boardDetail(id);
        List<BoardReplyDto> boardReplyDtoList=boardReplyService.boardReplyList(boardDto.getId());

        model.addAttribute("boardReplyDtoList",boardReplyDtoList);
        model.addAttribute("board",boardDto);
        return "board/boardAnswer";
    }



}












