package org.spring.e1i4TeamProject.board.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    List<BoardEntity> findByMemberEntity(MemberEntity memberEntity);

    Page<BoardEntity> findAllByCategory(Pageable pageable, Long category);

//  Page<BoardEntity> findByMemberAndCategory(Pageable pageable);

/*  @Query(value = "select * from board where category=8",nativeQuery = true)
  Page<BoardEntity> findByCategory(Pageable pageable);*/


//  Page<BoardEntity> findByCategory(Long id, Long category, Pageable pageable);



    @Modifying
    @Query(value = "update BoardEntity b set b.boardHit = b.boardHit+1 where b.id=:id")
    void boardHitById(@Param("id") Long id);


//    Page<BoardEntity> findByBoardTitleContains(Pageable pageable, String search);

    //    Page<BoardEntity> findByBoardContentContains(Pageable pageable, String search);
//////////////////////////////////////////////
    // 카테고리 1,2
    @Query(value = "select * from board where category=1 or category=2", nativeQuery = true)
    Page<BoardEntity> findByCategory1_2Contains(Pageable pageable);

    //카테고리가 1 or 2 + Title
    @Query(value = "  select * from board where (category=1 or category=2) and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle1_2Contains(Pageable pageable, @Param("search") String search);


    //카테고리가 1 or 2 + Content
    @Query(value = "  SELECT * FROM board where (category=1 or category=2) and board_content like %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent1_2Contains(Pageable pageable, @Param("search") String search);

    ////////////////////////////////
    //카테고리 3
    @Query(value = "select * from board where category=3", nativeQuery = true)
    Page<BoardEntity> findByCategory3Contains(Pageable pageable);

    //카테고리가 3+ Title
    @Query(value = "  SELECT * FROM board where category=3  and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle3Contains(Pageable pageable, @Param("search") String search);

    //카테고리가 3 + Content
    @Query(value = "  SELECT * FROM board where category=3 and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent3Contains(Pageable pageable, @Param("search") String search);

    //////////////////////////////////////////////// 4~7
    //카테고리 4~7
    @Query(value = "select * from board where category between 4 and 7", nativeQuery = true)
    Page<BoardEntity> findByCategory4_7Contains(Pageable pageable);


    //카테고리가 4~7+ Title
    @Query(value = "  SELECT * FROM board where (category=4 or category=5 or category=6 or category=7)  and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle4_7Contains(Pageable pageable, @Param("search") String search);

    //카테고리가  4~7 + Content
    @Query(value = "  SELECT * FROM board where (category=4 or category=5 or category=6 or category=7) and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent4_7Contains(Pageable pageable, @Param("search") String search);

    //////////////////////////////// 44444
    //카테고리 4
    @Query(value = "select * from board where category=4", nativeQuery = true)
    Page<BoardEntity> findByCategory4Contains(Pageable pageable);

    //카테고리가 4+ Title
    @Query(value = "  SELECT * FROM board where category=4  and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle4Contains(Pageable pageable, @Param("search") String search);

    //카테고리가 4 + Content
    @Query(value = "  SELECT * FROM board where category=4 and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent4Contains(Pageable pageable, @Param("search") String search);

    //////////////////////////////// 5555
    //카테고리 5
    @Query(value = "select * from board where category=5", nativeQuery = true)
    Page<BoardEntity> findByCategory5Contains(Pageable pageable);

    //카테고리가 5+ Title
    @Query(value = "  SELECT * FROM board where category=5  and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle5Contains(Pageable pageable, @Param("search") String search);

    //카테고리가 5 + Content
    @Query(value = "  SELECT * FROM board where category=5 and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent5Contains(Pageable pageable, @Param("search") String search);

    //////////////////////////////// 6666
    //카테고리 6
    @Query(value = "select * from board where category=6", nativeQuery = true)
    Page<BoardEntity> findByCategory6Contains(Pageable pageable);

    //카테고리가 6+ Title
    @Query(value = "  SELECT * FROM board where category=6 and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle6Contains(Pageable pageable, @Param("search") String search);

    //카테고리가 6 + Content
    @Query(value = "  SELECT * FROM board where category=6 and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent6Contains(Pageable pageable, @Param("search") String search);

    ////////////////////////////////
    //카테고리 7
    @Query(value = "select * from board where category=7", nativeQuery = true)
    Page<BoardEntity> findByCategory7Contains(Pageable pageable);

    //카테고리가 7+ Title
    @Query(value = "  SELECT * FROM board where category=7 and board_title LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardTitle7Contains(Pageable pageable, @Param("search") String search);

    //카테고리가 7 + Content
    @Query(value = "  SELECT * FROM board where category=7 and board_content LIKE %:search%"
            , nativeQuery = true)
    Page<BoardEntity> findByBoardContent7Contains(Pageable pageable, @Param("search") String search);


    @Query(value = "select * from board where category between 4 and 7", nativeQuery = true)
    List<BoardEntity> findByCategory();

    @Query(value = "select count(*) from board_reply r inner join board b on r.board_id = b.board_id " +
            "where r.board_id = :id", nativeQuery = true)
    int replyCount(@Param("id") Long id);


    @Query(value = "  SELECT * FROM board where category=8", nativeQuery = true)
    List<BoardEntity> findByCategory8(Long category);
}
