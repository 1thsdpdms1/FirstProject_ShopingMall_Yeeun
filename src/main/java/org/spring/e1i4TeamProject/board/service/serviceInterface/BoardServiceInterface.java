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

    void boardInsert(BoardDto boardDto);

    BoardDto boardDetail(Long id);


 /* List<BoardDto> boardMemberCategoryList(Long id, Long category);*/

    List<BoardDto> boardMemberCategoryList(Long id, Long category);

    BoardDto boardMemberCategorySave(BoardDto boardDto);


    Page<BoardDto> shopSearchPagingList(Pageable pageable, String subject, String search);

    Page<BoardDto> inquirySearchPagingList(Pageable pageable);
}
