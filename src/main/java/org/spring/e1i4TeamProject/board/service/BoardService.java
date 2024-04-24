package org.spring.e1i4TeamProject.board.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.board.dto.BoardDto;
import org.spring.e1i4TeamProject.board.dto.BoardFileDto;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardFileEntity;
import org.spring.e1i4TeamProject.board.repository.BoardFileRepository;
import org.spring.e1i4TeamProject.board.repository.BoardReplyRepository;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.spring.e1i4TeamProject.board.service.serviceInterface.BoardServiceInterface;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceInterface {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardReplyRepository boardReplyRepository;

    @Override// 파일 고려하기 않고 데이텀나 입력할 때
    public void boardInsert(BoardDto boardDto) {

        BoardEntity boardEntity = BoardEntity.toInsertBoardEntity0(boardDto);
        boardRepository.save(boardEntity);
    }

    @Override // 파일을 고려한 매서드
    public void boardInsertFile(BoardDto boardDto) throws IOException {
        System.out.println("if로 들어가지도 못함");
        if (boardDto.getBoardFile().isEmpty()) {
            //파일 없는 경우
            System.out.println("if첫 시작");
            //dto->entity
//            boardDto.setMemberEntity(MemberEntity.builder()
//                    .id(boardDto.getMemberEntity().getId())
//                    .build());
            //board 에 맞는 memberId 정보를 가져와야하는데 boardDto에 가져올 방법이
            // 없어서 MemberEntity로 바꿔서 boardDto로 가져온다.
            System.out.println("파일 없을때1");
            BoardEntity boardEntity = BoardEntity.toInsertBoardEntity0(boardDto);
            System.out.println("파일 없을때2");

            boardRepository.save(boardEntity);

        } else {
            //파일 있는 경우 -> 파일저장 별도의 테이블 생성 1:N
            //원본 파일, 이름 -> 서버 저장 이름
            // 저장 경로(실제 경로) -> 파일 저장
            // 테이블 저장(게시글 ,파일)
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////
            System.out.println("파일이 실제경로에 저장");
            MultipartFile boardFile = boardDto.getBoardFile();// 1. 파일을 가져온다. Dto
            // 이름 암호화 -> DB 저장, local에 저장 할 이름
            String oldFileName = boardFile.getOriginalFilename();// 원본파일 이름
            UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 플래스
            // ex) "gasdga_shop0.jpg"
            // ex) "gasdga"+"_"+"shop0.jpg"
            String newFileName = uuid + "_" + oldFileName; // 저장파일이름 (보완)

//            String filePath="C:/spring_savefile/"+newFileName; // 실제 파일 저장경로+이름
            String filePath = "C:/E1I4_file/" + newFileName; // 실제 파일 저장경로+이름
            //실제파일 저장 실행
            boardFile.transferTo(new File(filePath));//저장, 예외처리 -> 경로에 파일 저장
            //IOException
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////


            ///////////////////////////////////파일이 DB에 저장///////////////////////////
            // 1. 게시글 ->
            System.out.println("파일이 DB에 저장");
            boardDto.setMemberEntity(MemberEntity.builder()
                    .id(boardDto.getMemberEntity().getId())
                    .build());
            BoardEntity boardEntity = BoardEntity.toInsertBoardEntity1(boardDto);
            Long id = boardRepository.save(boardEntity).getId();


            Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
            if (optionalBoardEntity.isPresent()) { // -> 게시글이 존재한다면
                //게시글 0
                System.out.println("게시글이 존재한다면");
                BoardEntity boardEntity1 = optionalBoardEntity.get(); //Entity

                //게시글이 저장되면 -> 파일을 Entity에 저장

                BoardFileDto fileDto = BoardFileDto.builder().boardOldFileName(oldFileName)
                        .boardNewFileName(newFileName)
                        .boardEntity(boardEntity1)
                        .build();

                BoardFileEntity fileEntity = BoardFileEntity.toBoardInsertFile(fileDto);

                boardFileRepository.save(fileEntity);

            } else {
                throw new IllegalArgumentException("아이디가 없습니다");
            }


        }
    }

    @Override
    public List<BoardDto> boardList() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDtoList = boardEntityList.stream()
                .map(BoardDto::toboardDto)
                .collect(Collectors.toList());

        return boardDtoList;


    }

    @Override
    public BoardDto boardDetail(Long id) {

//        updateHit(id);

        Optional<BoardEntity> optionalBoardEntity
                = boardRepository.findById(id); //1.id를 찾는데 있는지 없는지부터 확인

        if (optionalBoardEntity.isPresent()) {
            //조회할 게시글이 있으면
            BoardEntity boardEntity
                    = optionalBoardEntity.get(); //2.  BoardEntity를 받아와서

            BoardDto boardDto = BoardDto.toBoardDetailDto(boardEntity);//3. entity -> dto로 바꿔주는 작업
            return boardDto;
        }
        throw new IllegalArgumentException("아이디가 Fail");

        //        return null;
        }




}

