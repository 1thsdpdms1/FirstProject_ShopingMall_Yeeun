package org.spring.e1i4TeamProject.board.controller;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.board.service.BoardReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reply")
public class ReplyController {
    private final BoardReplyService boardReplyService;

    @PostMapping("/replyWrite")
    public String replyWrite(BoardReplyDto boardReplyDto){

        boardReplyService.insertReply(boardReplyDto);

        return "redirect:/inquiry/Inquiry/"+boardReplyDto.getId();
    }

}
