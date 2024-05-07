package org.spring.e1i4TeamProject;

import org.junit.jupiter.api.Test;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.repository.BoardReplyRepository;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestDb {

    @Autowired
    BoardRepository boardRepository
            ;



    @Test
    void list(){


      List<BoardEntity> boardEntityList= boardRepository.findByMemberEntity(3L);

        System.out.println(boardEntityList);

    }

}
