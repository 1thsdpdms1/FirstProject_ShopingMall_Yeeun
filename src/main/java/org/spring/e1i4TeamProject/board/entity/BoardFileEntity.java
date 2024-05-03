package org.spring.e1i4TeamProject.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.board.dto.BoardFileDto;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "board_file")
public class BoardFileEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_file_id")
    private Long id;

    @Column(nullable = false)
    private String boardNewFileName;

    @Column(nullable = false)
    private String boardOldFileName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;


    //dto 작성페이지에서 작성후 entity로 넘기는 것
    public static BoardFileEntity toBoardInsertFile(BoardFileDto fileDto) {

        BoardFileEntity boardFileEntity=new BoardFileEntity();

        boardFileEntity.setBoardNewFileName(fileDto.getBoardNewFileName());
        boardFileEntity.setBoardOldFileName(fileDto.getBoardOldFileName());
        boardFileEntity.setBoardEntity(fileDto.getBoardEntity());

        return boardFileEntity;
    }

}
