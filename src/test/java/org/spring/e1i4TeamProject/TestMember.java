//package org.spring.e1i4TeamProject;
//
//import org.junit.jupiter.api.Test;
//<<<<<<< HEAD
//import org.spring.e1i4TeamProject.board.entity.BoardEntity;
//import org.spring.e1i4TeamProject.board.repository.BoardRepository;
//=======
//import org.spring.e1i4TeamProject.shop.entity.CartEntity;
//import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
//import org.spring.e1i4TeamProject.shop.repository.CartShopListRepository;
//>>>>>>> dev
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//@SpringBootTest
//public class TestMember {
//
//    @Autowired
//<<<<<<< HEAD
//    BoardRepository boardRepository;
//=======
//    CartShopListRepository cartShopListRepository;
//>>>>>>> dev
//
//    @Test
//    void member_idTest(){
//
//<<<<<<< HEAD
//        Pageable pageable = PageRequest.of(0, 10);
//
//      Page<BoardEntity> boardEntities
//          = boardRepository.findAllByCategory(pageable,8L);
//
//        System.out.println(boardEntities);
//=======
//       List<CartShopListEntity> cartShopListEntity
//            = cartShopListRepository.findByCartEntity(CartEntity.builder().id(1L).build());
//
//        System.out.println(cartShopListEntity.get(0));
//>>>>>>> dev
//
//    }
//}
