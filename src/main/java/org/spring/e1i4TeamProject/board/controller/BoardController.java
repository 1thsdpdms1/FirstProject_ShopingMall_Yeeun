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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final BoardReplyService boardReplyService;


    @GetMapping("/boardWrite")
    public String boardWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                             BoardDto boardDto, Model model){

        model.addAttribute("memberId",myUserDetails.getMemberEntity().getId());
        model.addAttribute("boardDto",boardDto);
        model.addAttribute("memberName",myUserDetails.getMemberEntity().getName());

        return "board/boardWrite";
    }


    @PostMapping("/boardWrite")
    public String boardWriteOK(BoardDto boardDto,
                               @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                               Model model) throws IOException {

//        model.addAttribute("memberName",myUserDetails.getMemberEntity().getName());
//        model.addAttribute("boardDto",boardDto);
//        boardService.boardInsert(boardDto);
        boardService.boardInsertFile(boardDto);

        return "redirect:/board/boardList";
        //글 작성후에 boardList 페이지로 이동
    }


    @GetMapping("/boardList")
    public String boardList( @AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                            @RequestParam(name = "subject",required = false) String subject,
                            @RequestParam(name = "search",required = false) String search,
                            @PageableDefault(page = 0, size = 3,sort = "id", direction = Sort.Direction.DESC)
                            Pageable pageable,Model model){
//        search
        Page<BoardDto> boardDtoList = boardService.boardSearchPageList(pageable,subject,search);

        model.addAttribute("myUserDetails",myUserDetails);

        //paging

        int totalPages = boardDtoList.getTotalPages(); // 전체 페이지
        int newPage=boardDtoList.getNumber(); // 현재 페이지
//        Long totalElements= boardDtoList.getTotalElements(); // 전체레코드 개수
//        int size = boardDtoList.getSize(); // 페이지당 보이는 갯수

        int blockNum = 8;// 브라우저에 보이는 페이지번호

        int startPage= (int)(
                (Math.floor(newPage/blockNum)*blockNum)+1<=totalPages?(Math.floor(newPage/blockNum)*blockNum) + 1 : totalPages
        );

        int endPage = (startPage + blockNum) -1 <totalPages ? (startPage + blockNum) -1 :totalPages;


        model.addAttribute("startPage",startPage);
        model.addAttribute("newPage",newPage);
        model.addAttribute("endPage",endPage);

        model.addAttribute("boardDtoList",boardDtoList);

        List<BoardDto> boardDtos =new ArrayList<>();
        if(subject==null || search ==null){
            boardDtos = boardService.boardList();
        }else{
            boardDtoList=  boardService.boardSearchPageList(pageable,subject,search);
        }

        return "board/boardList";
    }

    @Transactional
    @GetMapping("/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable("id")Long id){

        boardService.boardHit(id);

        //조회 -> BoardEntity id -> 파일이 있을 경우 FileEntity newFileName
        BoardDto board = boardService.boardDetail(id);

        //게시글이 존재하면 -> 게시글에 연결된 덧글리스트
//        List<BoardReplyDto> replyList= boardReplyService.boardReplyList(board.getId());

        model.addAttribute("board",board);
//        model.addAttribute("replyList",replyList);

        return "board/boardDetail";
    }

    @Transactional
    @GetMapping("/boardUpdate")
    public String boardUpdate(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                              @ModelAttribute BoardDto boardDto, Model model,
                              @PathVariable("id")Long id){

        BoardDto board = boardService.boardDetail(id);

        return "board/boardUpdate" + boardDto.getId();
    }




    @PostMapping("/boardUpdateOk")
    public String boardUpdateOk(@ModelAttribute BoardDto boardDto){

        boardService.boardUpdate(boardDto);

        return "redirect:/board/boardDetail" + boardDto.getId();
    }

    @Transactional
    @GetMapping("/boardDelete/{id}")
    public String delete(@PathVariable("id") Long id){

        boardService.boardDeleteById(id);

        return "redirect:/board/boardList";
    }





}




