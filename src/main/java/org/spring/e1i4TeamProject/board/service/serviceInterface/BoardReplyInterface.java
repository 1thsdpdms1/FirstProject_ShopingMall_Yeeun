package org.spring.e1i4TeamProject.board.service.serviceInterface;

import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardReplyInterface {
    void insertReply(BoardReplyDto boardReplyDto);

    List<BoardReplyDto> boardReplyList(Long id);

    // 심지섭
    void insertReply2(BoardReplyDto boardReplyDto);


    List<BoardReplyDto> boardReplyList2(Long id);

    Long boardReplyDeleteById(Long id);

}
