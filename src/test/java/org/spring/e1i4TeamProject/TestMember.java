package org.spring.e1i4TeamProject;

import org.junit.jupiter.api.Test;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class TestMember {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void member_idTest(){

        Pageable pageable = PageRequest.of(0, 10);

      Page<BoardEntity> boardEntities
          = boardRepository.findAllByCategory(pageable,8L);

        System.out.println(boardEntities);

    }
}
