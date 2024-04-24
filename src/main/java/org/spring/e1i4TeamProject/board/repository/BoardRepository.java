package org.spring.e1i4TeamProject.board.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHit=b.boardHit+1 where id=:id")
    void boardHitById(@Param("id") Long id);


//    Page<BoardEntity> findByBoardTitleContains(Pageable pageable, String search);

//    Page<BoardEntity> findByBoardContentContains(Pageable pageable, String search);
//////////////////////////////////////////////
    // 카테고리 1,2
    @Query(value = "select * from board where category=1 or category=2",nativeQuery = true)
    Page<BoardEntity> findByCategory1_2Contains(Pageable pageable);

    //카테고리가 1 or 2 + Title
    @Query(value = "  select * from board where (category=1 or category=2) and board_title like search=%:search%"
        ,nativeQuery = true)
    Page<BoardEntity> findByBoardTitle1_2Contains(Pageable pageable,@Param("search") String search);


    //카테고리가 1 or 2 + Content
    @Query(value = "  SELECT * FROM board where (category=1 or category=2) and board_content like `%:search%`  "
        ,nativeQuery = true)
    Page<BoardEntity> findByBoardContent1_2Contains(Pageable pageable,@Param("search") String search);

    ////////////////////////////////
    //카테고리 3
    @Query(value = "select * from board where category=3",nativeQuery = true)
    Page<BoardEntity> findByCategory3Contains(Pageable pageable);

    //카테고리가 3+ Title
    @Query(value = "  SELECT * FROM board where category=3  and board_title like search=%:search%   "
            ,nativeQuery = true)
    Page<BoardEntity> findByBoardTitle3Contains(Pageable pageable,@Param("search") String search);

    //카테고리가 3 + Content
    @Query(value = "  SELECT * FROM board where category=3 and board_content like `%:search%`  "
            ,nativeQuery = true)
    Page<BoardEntity> findByBoardContent3Contains(Pageable pageable,@Param("search") String search);

    ////////////////////////////////////////////////
    //카테고리 4~7
    @Query(value = "select * from board where category between 4 and 7",nativeQuery = true)
    Page<BoardEntity> findByCategory4_7Contains(Pageable pageable);

    //카테고리가 4~7+ Title
    @Query(value = "  SELECT * FROM board where between 4 and 7  and board_title like search=%:search%   "
            ,nativeQuery = true)
    Page<BoardEntity> findByBoardTitle4_7Contains(Pageable pageable,@Param("search") String search);

    //카테고리가  4~7 + Content
    @Query(value = "  SELECT * FROM board where between 4 and 7 and board_content like `%:search%`  "
            ,nativeQuery = true)
    Page<BoardEntity> findByBoardContent4_7Contains(Pageable pageable,@Param("search") String search);

}
