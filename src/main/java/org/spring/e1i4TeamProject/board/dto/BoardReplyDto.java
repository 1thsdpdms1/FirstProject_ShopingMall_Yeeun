package org.spring.e1i4TeamProject.board.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardReplyDto {

    private Long id;

    private String boardReplyWriter;

    private String boardReplyContent;

    private Long boardId;


    private BoardEntity boardEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    // 글 번호


    public static BoardReplyDto toSelectReplyDto(BoardReplyEntity boardReplyEntity) {
        BoardReplyDto boardReplyDto=new BoardReplyDto();
        boardReplyDto.setId(boardReplyEntity.getId());
        boardReplyDto.setBoardReplyWriter(boardReplyEntity.getBoardReplyWriter());
        boardReplyDto.setBoardReplyContent(boardReplyEntity.getBoardReplyContent());
        boardReplyDto.setBoardEntity(boardReplyEntity.getBoardEntity());
        boardReplyDto.setCreateTime(boardReplyEntity.getCreateTime());
        boardReplyDto.setUpdateTime(boardReplyEntity.getUpdateTime());

        return boardReplyDto;


    }
}
