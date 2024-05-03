package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;

import java.util.List;

public interface CartShopServiceImpl {

  List<CartShopListDto> cartList(Long id);

    List<CartShopListDto> memberCartList(Long id);

   void cartShopDelete(Long id);

  void deleteCart(List<Long> ids);
}
