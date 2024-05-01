package org.spring.e1i4TeamProject.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.board.repository.BoardReplyRepository;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.spring.e1i4TeamProject.board.service.serviceInterface.BoardReplyInterface;
import org.spring.e1i4TeamProject.board.service.serviceInterface.BoardServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardReplyService implements BoardReplyInterface {

    private  final BoardReplyRepository replyRepository;
    private final BoardRepository boardRepository;


    @Override
    public void insertReply(BoardReplyDto boardReplyDto) {

        // 글번호
        BoardEntity boardEntity= boardRepository.findById(boardReplyDto.getBoardId()).orElseThrow(()->{
            throw new IllegalArgumentException("아이디가 없습니다.");
        });

        if(boardEntity !=null){
            BoardReplyEntity boardReplyEntity = BoardReplyEntity.builder()
                    .boardEntity(BoardEntity.builder().id(boardReplyDto.getBoardId()).build())
                    .boardReplyContent(boardReplyDto.getBoardReplyContent())
                    .boardReplyWriter(boardReplyDto.getBoardReplyWriter())
                    .build();
//           boardReplyEntity.setBoardEntity(boardReplyDto);

//           boardReplyEntity = BoardReplyEntity.toInsertReplyEntity(boardReplyDto);
            replyRepository.save(boardReplyEntity);
        }

    }

    @Override
    public List<BoardReplyDto> boardReplyList(Long id) {
        BoardEntity boardEntity=boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당아이디를 찾을 수 없습니다.:"+id));
        List<BoardReplyEntity> boardReplyEntityList=replyRepository.findAllByBoardEntity(boardEntity);

        List<BoardReplyDto>boardReplyDtos=boardReplyEntityList.stream()
                .map(BoardReplyDto::toSelectReplyDto)
                .collect(Collectors.toList());

        return boardReplyDtos;
    }
}
