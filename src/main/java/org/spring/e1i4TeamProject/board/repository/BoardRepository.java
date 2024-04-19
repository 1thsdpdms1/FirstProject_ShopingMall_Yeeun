package org.spring.e1i4TeamProject.board.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHit=b.boardHit+1 where id=:id")
    void boardHitById(@Param("id") Long id);

    Page<BoardEntity> findByBoardTitleContaining(Pageable pageable, String search);

    Page<BoardEntity> findByBoardContentContaining(Pageable pageable, String search);
}
