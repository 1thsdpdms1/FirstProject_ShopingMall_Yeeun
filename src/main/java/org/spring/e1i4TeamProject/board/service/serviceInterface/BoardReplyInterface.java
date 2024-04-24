package org.spring.e1i4TeamProject.board.service.serviceInterface;

import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;

import java.util.List;

public interface BoardReplyInterface {
    void insertReply(BoardReplyDto boardReplyDto);

    List<BoardReplyDto> boardReplyList(Long id);
}
