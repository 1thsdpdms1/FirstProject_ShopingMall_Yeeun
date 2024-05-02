package org.spring.e1i4TeamProject.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.board.repository.BoardReplyRepository;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.spring.e1i4TeamProject.board.service.BoardReplyService;
import org.spring.e1i4TeamProject.board.service.BoardService;
import org.spring.e1i4TeamProject.config.MyUserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reply")
public class ReplyController {
    private final BoardReplyService boardReplyService;
    private final BoardService boardService;
    private final BoardReplyRepository boardReplyRepository;
    private final BoardRepository boardRepository;


    @PostMapping("/replyWrite")
    public String replyWrite(BoardReplyDto boardReplyDto) {

        boardReplyService.insertReply(boardReplyDto);

        return "redirect:/inquiry/Inquiry/" + boardReplyDto.getId();
    }


    ///심지섭///////////////////////////////////////////////
    @PostMapping("/boardReplyWrite")
    public String boardReplyWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                                  Model model, BoardReplyDto boardReplyDto, BoardDto boardDto) {

        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());
        boardReplyService.insertReply2(boardReplyDto);

        return "redirect:/board/boardDetail/" + boardReplyDto.getBoardId();
    }

    @PostMapping("/noticeBoardReplyWrite")
    public String noticeReplyWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                                  Model model, BoardReplyDto boardReplyDto, BoardDto boardDto) {

        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());
        boardReplyService.insertReply2(boardReplyDto);

        return "redirect:/board/noticeBoardDetail/" + boardReplyDto.getBoardId();
    }

    @PostMapping("/reviewBoardReplyWrite")
    public String reviewBoardReplyWrite(@AuthenticationPrincipal MyUserDetailsImpl myUserDetails,
                                  Model model, BoardReplyDto boardReplyDto, BoardDto boardDto) {

        model.addAttribute("memberName", myUserDetails.getMemberEntity().getName());
        boardReplyService.insertReply2(boardReplyDto);

        return "redirect:/board/reviewBoardDetail/" + boardReplyDto.getBoardId();
    }

    //커뮤니티 댓글 삭제
    @GetMapping("/boardReplyDelete/{id}")
    public String boardReplyDelete(@PathVariable("id") Long id) {
        Long boardId = boardReplyService.boardReplyDeleteById(id);

        return "redirect:/board/boardDetail/" + boardId;
    }


    //공지사항 댓글 삭제
    @GetMapping("/noticeReplyDelete/{id}")
    public String noticeBoardReplyDelete(@PathVariable("id") Long id) {
        Long boardId = boardReplyService.boardReplyDeleteById(id);

        return "redirect:/board/noticeBoardDetail/" + boardId;
    }

    //수강후기 댓글 삭제
    @GetMapping("/reviewReplyDelete/{id}")
    public String reviewBoardReplyDelete(@PathVariable("id") Long id) {
        Long boardId = boardReplyService.boardReplyDeleteById(id);

        return "redirect:/board/reviewBoardDetail/" + boardId;
    }


}
