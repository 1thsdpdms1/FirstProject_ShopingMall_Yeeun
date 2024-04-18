package org.spring.e1i4TeamProject.board.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardFileEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardDto {

    private Long id;

    public Long category;

    private String boardTitle;

    private String boardContent;

    private String boardWriter;

    private int boardHit;

    private int boardAttachFile;

    private MultipartFile boardFile;

    private MemberEntity memberEntity;

    private List<BoardFileEntity> boardFileEntityList;

    private List<BoardReplyEntity> boardReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    //Entity -> Dto (데이터 가져올때)
    public static BoardDto toboardDto(BoardEntity boardEntity) {
        BoardDto boardDto=new BoardDto();

        boardDto.setId(boardEntity.getId());
        boardDto.setBoardContent(boardEntity.getBoardContent());
        boardDto.setCategory(boardEntity.getCategory());
        boardDto.setBoardWriter(boardEntity.getBoardWriter()); // 작성말고 회원의 이름정보만 받아오기?
        boardDto.setBoardHit(boardEntity.getBoardHit());
        boardDto.setBoardAttachFile(boardEntity.getBoardAttachFile());
        boardDto.setBoardReplyEntityList(boardEntity.getBoardReplyEntityList());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());

        return boardDto;

    }
    //엔티티 -> DTO
    public static BoardDto toBoardDetailDto(BoardEntity boardEntity) {

        BoardDto boardDto = new BoardDto();

        boardDto.setId(boardEntity.getId());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setCategory(boardEntity.getCategory());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardContent(boardEntity.getBoardContent());
        boardDto.setBoardHit(boardEntity.getBoardHit());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());
        boardDto.setBoardFileEntityList(boardEntity.getBoardFileEntityList());
        boardDto.setBoardReplyEntityList(boardEntity.getBoardReplyEntityList());

        return boardDto;

    }
}
