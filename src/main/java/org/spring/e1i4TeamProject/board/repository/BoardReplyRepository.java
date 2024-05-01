package org.spring.e1i4TeamProject.board.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Long> {
    List<BoardReplyEntity> findAllByBoardEntity(BoardEntity boardEntity);

}
