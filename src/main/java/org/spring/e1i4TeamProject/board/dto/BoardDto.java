package org.spring.e1i4TeamProject.board.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardFileEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardDto {

    private Long id;

    private String boardTitle;

    private String boardContent;

    private String boardWriter;

    private int boardHit;

    private int boardAttachFile;

    private MemberEntity memberEntity;

    private List<BoardFileEntity> boardFileEntityList;

    private List<BoardReplyEntity> boardReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
