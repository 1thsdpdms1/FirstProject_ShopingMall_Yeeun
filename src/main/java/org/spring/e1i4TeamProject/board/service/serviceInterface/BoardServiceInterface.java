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


    Page<BoardDto> boardSearchPageList4(Pageable pageable, String subject, String search);

    //카테고리 5만
    Page<BoardDto> boardSearchPageList5(Pageable pageable, String subject, String search);

    //카테고리 6만
    Page<BoardDto> boardSearchPageList6(Pageable pageable, String subject, String search)//카테고리 5만
    ;

    //카테고리 7만
    Page<BoardDto> boardSearchPageList7(Pageable pageable, String subject, String search)//카테고리 6만
    ;

    BoardDto boardDetail(Long id);

    void boardHit(Long id);

    void boardDeleteById(Long id);

    void boardUpdate(BoardDto boardDto) throws IOException;

    Page<BoardDto> boardSearchPageList1_2(Pageable pageable, String subject, String search);

    Page<BoardDto> boardSearchPageList3(Pageable pageable, String subject, String search);

    Page<BoardDto> boardSearchPageList4_7(Pageable pageable, String subject, String search);


 /* List<BoardDto> boardMemberCategoryList(Long id, Long category);*/

    List<BoardDto> boardMemberCategoryList(Long id, Long category);

    BoardDto boardMemberCategorySave(BoardDto boardDto);

    Page<BoardDto> inquirySearchPagingList(Pageable pageable);

    List<BoardDto> boardInquiryList(Long category);

    List<BoardDto> topReviewBoardList(BoardDto boardDto);
}
