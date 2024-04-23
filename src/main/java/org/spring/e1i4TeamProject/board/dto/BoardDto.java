package org.spring.e1i4TeamProject.board.dto;

import lombok.*;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardFileEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private Long memberId;

    private String newFileName;

    private String oldFileName;



    //Entity -> Dto (데이터 가져올때) List
    public static BoardDto toboardDto(BoardEntity boardEntity) {
        BoardDto boardDto=new BoardDto();

        boardDto.setId(boardEntity.getId());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setBoardContent(boardEntity.getBoardContent());
        boardDto.setCategory(boardEntity.getCategory());
        boardDto.setBoardWriter(boardEntity.getBoardWriter()); // 작성말고 회원의 이름정보만 받아오기?
        boardDto.setBoardHit(boardEntity.getBoardHit());
        boardDto.setBoardAttachFile(boardEntity.getBoardAttachFile());
        boardDto.setBoardReplyEntityList(boardEntity.getBoardReplyEntityList());
        boardDto.setMemberEntity(boardEntity.getMemberEntity());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());

        return boardDto;

    }
    //엔티티 -> DTO  Detail
    public static BoardDto toBoardDetailDto(BoardEntity boardEntity) {

        BoardDto boardDto = new BoardDto();
BoardFileEntity boardFileEntity = new BoardFileEntity();

        List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

        boardDto.setId(boardEntity.getId());
        boardDto.setMemberId(boardEntity.getId());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setCategory(boardEntity.getCategory());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardContent(boardEntity.getBoardContent());
        boardDto.setBoardHit(boardEntity.getBoardHit());
        boardDto.setBoardAttachFile(boardEntity.getBoardAttachFile());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());
        boardDto.setMemberEntity(boardEntity.getMemberEntity());
        if(boardEntity.getBoardAttachFile()==0){
            //파일이 X
            boardDto.setBoardAttachFile(boardEntity.getBoardAttachFile());

        }else{
            // 파일이 있으면
            boardDto.setBoardAttachFile(boardEntity.getBoardAttachFile());

            // 새이름
            boardDto.setNewFileName(boardEntity.getBoardFileEntityList().get(0).getBoardNewFileName());

            //원래 이름
            boardDto.setOldFileName(boardEntity.getBoardFileEntityList().get(0).getBoardOldFileName());

        }

        boardDto.setBoardReplyEntityList(boardEntity.getBoardReplyEntityList());

        return boardDto;

    }
}
