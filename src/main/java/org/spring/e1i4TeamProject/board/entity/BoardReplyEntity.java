package org.spring.e1i4TeamProject.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.board.dto.BoardReplyDto;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "board_reply")
public class BoardReplyEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_reply_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String boardReplyWriter;

    @Column(nullable = false, length = 500)
    private String boardReplyContent;

    @JsonIgnore // ajax시 순환0참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static BoardReplyEntity toInsertReplyEntity(BoardReplyDto boardReplyDto) {
        BoardReplyEntity boardReplyEntity=new BoardReplyEntity();

        boardReplyEntity.setBoardEntity(boardReplyDto.getBoardEntity());
        boardReplyEntity.setBoardReplyContent(boardReplyDto.getBoardReplyContent());
        boardReplyEntity.setBoardReplyWriter(boardReplyDto.getBoardReplyWriter());


        return boardReplyEntity;



    }
}
