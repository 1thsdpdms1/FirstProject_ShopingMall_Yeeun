package org.spring.e1i4TeamProject;

import org.junit.jupiter.api.Test;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.spring.e1i4TeamProject.shop.repository.CartShopListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMember {

    @Autowired
    CartShopListRepository cartShopListRepository;

    @Test
    void member_idTest(){

       List<CartShopListEntity> cartShopListEntity
            = cartShopListRepository.findByCartEntity(CartEntity.builder().id(1L).build());

        System.out.println(cartShopListEntity.get(0));

    }
}
