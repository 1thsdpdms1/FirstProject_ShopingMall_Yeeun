package org.spring.e1i4TeamProject;

import org.junit.jupiter.api.Test;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
<<<<<<< HEAD
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
>>>>>>> dev
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

import java.util.List;

@SpringBootTest
class E1i4TeamProjectApplicationTests {

	@Autowired
	BoardRepository boardRepository;

	@Test
	void contextLoads() {

<<<<<<< HEAD

		List<BoardEntity> list= boardRepository.findByMemberEntity(MemberEntity.builder().id(1L).build());

		System.out.println(list.toString());
=======
//		List<BoardEntity> list=boardRepository.findByPagingBoard();
//
//		System.out.println(list.toString());


//		Pageable pageable=null;
//		Page<BoardEntity> page=boardRepository.findByCategoryContaining(pageable,1L);

//		System.out.println(page);
>>>>>>> dev
	}

}
