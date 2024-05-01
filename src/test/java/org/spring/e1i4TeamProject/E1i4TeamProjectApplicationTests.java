package org.spring.e1i4TeamProject;

import org.junit.jupiter.api.Test;
import org.spring.e1i4TeamProject.board.entity.BoardEntity;
import org.spring.e1i4TeamProject.board.repository.BoardRepository;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class E1i4TeamProjectApplicationTests {

	@Autowired
	BoardRepository boardRepository;

	@Test
	void contextLoads() {


		List<BoardEntity> list= boardRepository.findByMemberEntity(MemberEntity.builder().id(1L).build());

		System.out.println(list.toString());
	}

}
