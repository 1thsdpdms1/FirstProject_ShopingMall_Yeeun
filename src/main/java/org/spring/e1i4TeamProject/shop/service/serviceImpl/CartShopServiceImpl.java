package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartShopServiceImpl {

  List<CartShopListDto> cartList(Long id);
}
