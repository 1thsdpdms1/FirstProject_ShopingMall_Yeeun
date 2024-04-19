package org.spring.e1i4TeamProject.board.service.serviceInterface;

import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardServiceInterface {
    //파일까지
    void boardInsertFile(BoardDto boardDto) throws IOException;

    List<BoardDto> boardList();

    BoardDto boardDetail(Long id);

    void boardHit(Long id);

    void boardDeleteById(Long id);

    void boardUpdate(BoardDto boardDto);

    Page<BoardDto> boardSearchPageList(Pageable pageable, String subject, String search);
}
