package org.spring.e1i4TeamProject.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.board.repository.BoardReplyRepository;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.spring.e1i4TeamProject.board.service.serviceInterface.BoardReplyInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardReplyService implements BoardReplyInterface {

    private final BoardReplyRepository boardReplyRepository;
    private final BoardRepository boardRepository;


    @Override
    public void insertReply(BoardReplyDto boardReplyDto) {

        // 글번호
        BoardEntity boardEntity = boardRepository.findById(boardReplyDto.getBoardEntity().getId()).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디가 없습니다.");
        });

        if (boardEntity != null) {
            BoardEntity boardEntity1 = BoardEntity.builder()
                    .id(boardReplyDto.getId())
                    .build();
            boardReplyDto.setBoardEntity(boardEntity1);

            BoardReplyEntity boardReplyEntity = BoardReplyEntity.toInsertReplyEntity(boardReplyDto);
            boardReplyRepository.save(boardReplyEntity);
        }

    }

    @Override
    public List<BoardReplyDto> boardReplyList(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당아이디를 찾을 수 없습니다.:" + id));
        List<BoardReplyEntity> boardReplyEntityList = boardReplyRepository.findAllByBoardEntity(boardEntity);

        List<BoardReplyDto> boardReplyDtos = boardReplyEntityList.stream()
                .map(BoardReplyDto::toSelectReplyDto)
                .collect(Collectors.toList());

        return boardReplyDtos;
    }


    // 심지섭//////////////////////////////////////////////
    @Override
    public void insertReply2(BoardReplyDto boardReplyDto) {

        // 해당 글번호가 있는지 찾기
        BoardEntity boardEntity = boardRepository.findById(boardReplyDto.getBoardId()).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디가 없습니다.");
        });

        //해당 글이 null이 아니라면
        if (boardEntity != null) {
             BoardReplyEntity boardReplyEntity = BoardReplyEntity.builder()
                    .boardEntity(BoardEntity.builder()
                        .id(boardReplyDto.getBoardId()).build()) // 글의 아이디
                    .boardReplyWriter(boardReplyDto.getBoardReplyWriter())
                    .boardReplyContent(boardReplyDto.getBoardReplyContent())
                    .build(); // 찾아서 값 넣고
            boardReplyRepository.save(boardReplyEntity);
        }
    }

    @Override
    public List<BoardReplyDto> boardReplyList2(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글을 찾을 수 없습니다.:" + id));
        List<BoardReplyEntity> boardReplyEntityList = boardReplyRepository.findAllByBoardEntity(boardEntity);

        List<BoardReplyDto> boardReplyDtoList = boardReplyEntityList.stream()
                .map(BoardReplyDto::toSelectReplyDto)
                .collect(Collectors.toList());

        return boardReplyDtoList;
    }

    @Transactional
    @Override
    public Long boardReplyDeleteById(Long id) {

        //해당 게시글 id를 찾아라
//       boardReplyRepository.findById(BoardReplyDto.builder().boardId(id).build().getId()).orElseThrow(()
//                  -> new IllegalArgumentException("해당아이디를 찾을 수 없습니다.:"));

        Long boardId = boardReplyRepository.findById(id).get().getBoardEntity().getId(); //댓글 id를 찾아라

        if (boardId != null){
            boardReplyRepository.deleteById(id);
        }else{
            System.out.println("댓글삭제 실패");
        }

        return boardId;
    }


}
