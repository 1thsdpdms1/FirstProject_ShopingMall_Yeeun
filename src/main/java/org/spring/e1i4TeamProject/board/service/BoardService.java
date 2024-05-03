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
import org.spring.e1i4TeamProject.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


<<<<<<< HEAD
=======


>>>>>>> dev
@Transactional
@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceInterface {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardReplyRepository boardReplyRepository;


    @Override // 파일을 고려한 매서드
    public void boardInsertFile(BoardDto boardDto) throws IOException {

        if (boardDto.getBoardFile().isEmpty()) {
            //파일 없는 경우
            //dto->entity
            boardDto.setMemberEntity(MemberEntity.builder()
                    .id(boardDto.getMemberId())
                    .build());
            //board 에 맞는 memberId 정보를 가져와야하는데 boardDto에 가져올 방법이
            // 없어서 MemberEntity로 바꿔서 boardDto로 가져온다.
            BoardEntity boardEntity = BoardEntity.toInsertBoardEntity0(boardDto);

            boardRepository.save(boardEntity);

        } else {
            //파일 있는 경우 -> 파일저장 별도의 테이블 생성 1:N
            //원본 파일, 이름 -> 서버 저장 이름
            // 저장 경로(실제 경로) -> 파일 저장
            // 테이블 저장(게시글 ,파일)
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////
            MultipartFile boardFile = boardDto.getBoardFile();// 1. 파일을 가져온다. Dto
            // 이름 암호화 -> DB 저장, local에 저장 할 이름
            String oldFileName = boardFile.getOriginalFilename();// 원본파일 이름
            UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 플래스
            // ex) "gasdga_board0.jpg"
            // ex) "gasdga"+"_"+"board0.jpg"
            String newFileName = uuid + "_" + oldFileName; // 저장파일이름 (보완)

//            String filePath="C:/spring_savefile/"+newFileName; // 실제 파일 저장경로+이름
            String filePath = "C:/E1I4_file/" + newFileName; // 실제 파일 저장경로+이름
            //실제파일 저장 실행
            boardFile.transferTo(new File(filePath));//저장, 예외처리 -> 경로에 파일 저장
            //IOException
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////


            ///////////////////////////////////파일이 DB에 저장///////////////////////////
            // 1. 게시글 ->
            boardDto.setMemberEntity(MemberEntity.builder()
                    .id(boardDto.getMemberId())
                    .build());
            BoardEntity boardEntity = BoardEntity.toInsertBoardEntity1(boardDto);
            Long id = boardRepository.save(boardEntity).getId();


            Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
            if (optionalBoardEntity.isPresent()) { // -> 게시글이 존재한다면
                //게시글 0
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

        List<BoardEntity> boardEntityList = boardRepository.findByCategory();
        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDtoList = boardEntityList.stream()
                .map(BoardDto::toboardDto)
                .collect(Collectors.toList());

        return boardDtoList;
    }




    @Override
    public Page<BoardDto> boardSearchPageList1_2(Pageable pageable, String subject, String search) {
//        BoardEntity boardEntity=new BoardEntity();

        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory1_2Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle1_2Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent1_2Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory1_2Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }

        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toboardDto);

        return boardDtoPage;
    }

    @Override
    public Page<BoardDto> boardSearchPageList3(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory3Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle3Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent3Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory3Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toboardDto);

        return boardDtoPage;
    }

    //카테고리 4~7
    @Override
    public Page<BoardDto> boardSearchPageList4_7(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;

        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory4_7Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle4_7Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent4_7Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory4_7Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);

        return boardDtoPage;
    }

    //카테고리 4만
    @Override
    public Page<BoardDto> boardSearchPageList4(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory4Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle4Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent4Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory4Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);

        return boardDtoPage;
    }//카테고리 4만

    //카테고리 5만
    @Override
    public Page<BoardDto> boardSearchPageList5(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory5Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle5Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent5Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory5Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);

        return boardDtoPage;
    }//카테고리 5만

    //카테고리 6만
    @Override
    public Page<BoardDto> boardSearchPageList6(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory6Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle6Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent6Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory6Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);

        return boardDtoPage;
    }//카테고리 6만

    //카테고리 7만
    @Override
    public Page<BoardDto> boardSearchPageList7(Pageable pageable, String subject, String search) {
        BoardEntity boardEntity=new BoardEntity();
        Page<BoardEntity> boardEntityPage = null;


        if(subject==null || search==null){
            boardEntityPage = boardRepository.findByCategory7Contains(pageable);
        }else {
            if (subject.equals("boardTitle")){
                boardEntityPage=boardRepository.findByBoardTitle7Contains(pageable,search);
            } else if (subject.equals("boardContent")) {
                boardEntityPage=boardRepository.findByBoardContent7Contains(pageable,search);
            }else {
                boardEntityPage= boardRepository.findByCategory7Contains(pageable);
            }
        }
        for (BoardEntity boardEntity1 : boardEntityPage) {
            int replyCount = boardRepository.replyCount(boardEntity1.getId());
            boardEntity1.setReplyCount(replyCount);
        }
        Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);

        return boardDtoPage;
    }//카테고리 7만

//    @Override//paging
//    public Page<BoardDto> boardPageList(Pageable pageable) {
//
//        Page<BoardEntity> pagingEntity = boardRepository.findAll(pageable);
//
//        // paging처리 -> 페이징 객체 하나 BoardEntity -> BoardDto의 toSelectBoardDto 매서드에 추가
//        Page<BoardDto> boardDtos= pagingEntity.map(BoardDto::toboardDto);
//
////        pagingEntity.get().forEach(boardEntity -> {
////            BoardDto.toSelectBoardDto(boardEntity);
////        });
//        //하나씩 꺼내서 작업하는 것
//
//        return boardDtos;
//    }
    @Transactional
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
    @Transactional
    @Override
    public void boardHit(Long id) {

        boardRepository.boardHitById(id);
    }

    @Transactional
    @Override
    public void boardUpdate(BoardDto boardDto) throws IOException {

        //게시물 유무 체크
        BoardEntity boardEntity =  boardRepository.findById(boardDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("수정할 게시글 없음"));

        //파일체크
        Optional<BoardFileEntity> optionalBoardFileEntity = boardFileRepository.findByBoardEntityId(boardDto.getId());

//        파일이 있으면 파일 기존 파일 삭제
        if (optionalBoardFileEntity.isPresent()) {
            String fileNewName = optionalBoardFileEntity.get().getBoardNewFileName();
            String filePath = "C:/E1I4_file/" + fileNewName;
            File deleteFile = new File(filePath);
            if (deleteFile.exists()) {
                deleteFile.delete();
                System.out.println("파일을 삭제하였습니다");
            } else {
                System.out.println("파일이 존재하지않습니다");
            }
            boardFileRepository.delete(optionalBoardFileEntity.get());//파일 테이블 레코드 삭제
        }
//        //수정
        MemberEntity memberEntity = MemberEntity.builder()
                .id(boardDto.getMemberId()).build();
        boardDto.setMemberEntity(memberEntity);

        if (boardDto.getBoardFile().isEmpty()) {
            //파일 없는경우
            boardEntity = BoardEntity.toBoardUpdateEntity0(boardDto);
            boardRepository.save(boardEntity);
        } else {
            //파일있는경우
            MultipartFile boardFile = boardDto.getBoardFile();
            String fileOldName = boardFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String fileNewName = uuid + "_" + fileOldName;
            String savePath = "C:/E1I4_file/" + fileNewName;
            boardFile.transferTo(new File(savePath));

            boardEntity = BoardEntity.toBoardUpdateEntity1(boardDto);
            boardRepository.save(boardEntity);

            BoardFileEntity boardFileEntity = BoardFileEntity.builder()
                    .boardEntity(boardEntity)
                    .boardNewFileName(fileNewName)
                    .boardOldFileName(fileOldName)
                    .build();

            Long fileId = boardFileRepository.save(boardFileEntity).getId();
            boardFileRepository.findById(fileId).orElseThrow(() -> {
                throw new IllegalArgumentException("파일등록 실패");
            });
        }
        //게시글 수정 확인
        boardRepository.findById(boardDto.getId()).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글 수정실패");
        });
}

    @Transactional
    @Override
    public void boardDeleteById(Long id) {

        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (boardEntity.isPresent()){
            boardRepository.deleteById(id);
        }
        System.out.println("삭제 불가능");

    }

<<<<<<< HEAD
    
    //조민성씨
=======
>>>>>>> dev
    @Override
    public List<BoardDto> topReviewBoardList(BoardDto boardDto) {

        List<BoardEntity> boardEntityList = boardRepository.findTop3();

        List<BoardDto> boardDtoList = boardEntityList.stream()
                .map(BoardDto::toboardDto).collect(Collectors.toList());

        return boardDtoList;

    }


    /////////

    @Override
    public BoardDto boardMemberCategorySave(BoardDto boardDto) {

        Long id= boardRepository.save(BoardEntity.toInsertBoardEntity0(boardDto)).getId();

        BoardEntity boardEntity= boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return BoardDto.toboardDto1(boardEntity);
    }

//    @Override
//    public Page<BoardDto> getBoardMemberCategoryPagingList(Pageable pageable, Long id, Long category) {
//        Page<BoardEntity> boardEntityList = boardRepository.findByMemberAndCategory(id, category, pageable); // 적절한 필터링 메서드로 대체
//
//        return boardEntityList.map(BoardDto::toselectBoardDto);
//
//    }



    @Override
    public Page<BoardDto> inquirySearchPagingList(Pageable pageable) {

      Long category=8L;

       Page<BoardEntity> pageList=boardRepository.findAllByCategory(pageable,category);

        return pageList.map(BoardDto::toselectBoardDto);
    }

    @Override
    public List<BoardDto> boardMemberCategoryList(Long id, Long category) {

        MemberEntity memberEntity= MemberEntity.builder().id(id).build();

        List<BoardEntity> boardEntityList
                =boardRepository.findByMemberEntity(memberEntity);

        for(BoardEntity boardEntity: boardEntityList){
            System.out.println(boardEntity);
        }

        return boardEntityList.stream().map(BoardDto::toboardDto2).collect(Collectors.toList());
    }


//    @Override
//    public List<BoardDto> boardInquiryList(Long category) {
//
//        MemberEntity memberEntity=MemberEntity.builder().build();
//
//        List<BoardEntity> boardEntityList
//                =boardRepository.findByMemberEntity(memberEntity);
//
//        for(BoardEntity boardEntity: boardEntityList){
//            System.out.println(boardEntity);
//        }
//        return boardEntityList.stream().map(BoardDto::toboardDto2).collect(Collectors.toList());
//
//    }

//    심지섭
    @Override
    public List<BoardDto> boardInquiryList(Long category) {

        List<BoardEntity> boardEntityList = boardRepository.findByCategory8(category);
//    List<BoardDto> boardDtoList = new ArrayList<>();

        List<BoardDto>  boardDtoList = boardEntityList.stream()
            .map(BoardDto::toboardDto8)
                .collect(Collectors.toList());

        return boardDtoList;

    }
    /////////////////
//    BoardEntity boardEntity=new BoardEntity();
//    Page<BoardEntity> boardEntityPage = null;
//
//
//        if(subject==null || search==null){
//        boardEntityPage = boardRepository.findByCategory6Contains(pageable);
//    }else {
//        if (subject.equals("boardTitle")){
//            boardEntityPage=boardRepository.findByBoardTitle6Contains(pageable,search);
//        } else if (subject.equals("boardContent")) {
//            boardEntityPage=boardRepository.findByBoardContent6Contains(pageable,search);
//        }else {
//            boardEntityPage= boardRepository.findByCategory6Contains(pageable);
//        }
//    }
//        for (BoardEntity boardEntity1 : boardEntityPage) {
//        int replyCount = boardRepository.replyCount(boardEntity1.getId());
//        boardEntity1.setReplyCount(replyCount);
//    }
//    Page<BoardDto> boardDtoPage = boardEntityPage.map(BoardDto::toBoardDetailDto);
//
//        return boardDtoPage;




}

