package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

<<<<<<< HEAD
=======
//@Repository
>>>>>>> dev
public interface CartShopServiceImpl {

  List<CartShopListDto> cartList(Long id);

    List<CartShopListDto> memberCartList(Long id);
}
