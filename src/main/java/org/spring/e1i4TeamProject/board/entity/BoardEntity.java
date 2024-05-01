package org.spring.e1i4TeamProject.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    public Long category;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false)
    private String boardWriter;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int boardHit;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int replyCount;

    @Column(nullable = false)
    private int boardAttachFile; //게시글 작성시 파일이 존재하면 1, 없으면 0

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardFileEntity> boardFileEntityList;

    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "boardEntity"
        , fetch = FetchType.LAZY
        , cascade = CascadeType.REMOVE)
    private List<BoardReplyEntity> boardReplyEntityList;

    //board write dto 데이터를 Entity 로 보낸다 - 파일 없다 -> 정상 실행
    public static BoardEntity toInsertBoardEntity0(BoardDto boardDto) {

        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setCategory(boardDto.getCategory());
        boardEntity.setBoardTitle(boardDto.getBoardTitle()); //게시글 제목
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardAttachFile(0);
        boardEntity.setBoardFileEntityList(boardDto.getBoardFileEntityList());//게시글 파일
        boardEntity.setBoardHit(0);
        boardEntity.setReplyCount(boardDto.getReplyCount());
        boardEntity.setMemberEntity(boardDto.getMemberEntity());

        return boardEntity;
    }

    //board write dto 데이터를 Entity 로 보낸다 - 파일 있다
    public static BoardEntity toInsertBoardEntity1(BoardDto boardDto) {

        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setCategory(boardDto.getCategory());
        boardEntity.setBoardTitle(boardDto.getBoardTitle()); //게시글 제목
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());//게시글 작성자
        boardEntity.setBoardAttachFile(1);
        boardEntity.setBoardFileEntityList(boardDto.getBoardFileEntityList());//게시글 파일
        boardEntity.setReplyCount(boardDto.getReplyCount());
        boardEntity.setBoardHit(0);
        boardEntity.setMemberEntity(boardDto.getMemberEntity());

        return boardEntity;
    }


    //파일이 없을 때 수정
    public static BoardEntity toBoardUpdateEntity0(BoardDto boardDto) {

        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setId(boardDto.getId());
        boardEntity.setCategory(boardDto.getCategory());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());//게시글 작성자
        boardEntity.setBoardFileEntityList(boardDto.getBoardFileEntityList());//게시글 파일
        boardEntity.setBoardAttachFile(0);
        boardEntity.setMemberEntity(boardDto.getMemberEntity());

        return boardEntity;
    }


    //파일이 있을 때 수정
    public static BoardEntity toBoardUpdateEntity1(BoardDto boardDto) {

        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setId(boardDto.getId());
        boardEntity.setCategory(boardDto.getCategory());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardAttachFile(1);
        boardEntity.setBoardFileEntityList(boardDto.getBoardFileEntityList());
        boardEntity.setMemberEntity(boardDto.getMemberEntity());

        return boardEntity;
    }

}
