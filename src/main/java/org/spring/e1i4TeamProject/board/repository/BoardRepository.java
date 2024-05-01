package org.spring.e1i4TeamProject.board.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

}
