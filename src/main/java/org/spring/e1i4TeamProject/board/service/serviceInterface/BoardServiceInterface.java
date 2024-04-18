package org.spring.e1i4TeamProject.board.service.serviceInterface;

import org.spring.e1i4TeamProject.board.dto.BoardDto;

import java.io.IOException;
import java.util.List;

public interface BoardServiceInterface {
    //파일까지
    void boardInsertFile(BoardDto boardDto) throws IOException;

    List<BoardDto> boardList();

    void boardInsert(BoardDto boardDto);

    BoardDto boardDetail(Long id);
}
