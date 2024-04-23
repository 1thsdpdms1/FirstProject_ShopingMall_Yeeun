package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.CartShopListDto;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface ShopServiceImpl {
  void insertShop(ShopDto shopDto) throws IOException;

  void updateShopHit(Long id);

  ShopDto detail(Long id);

  Page<ShopDto> shopSearchPagingList(Pageable pageable, String subject, String search);

  void shopUpdateOk(ShopDto shopDto) throws IOException;

  void shopDelete(Long id);

  void addCart(Long id, Long shopId);

  List<ShopDto> shopList1();
  List<ShopDto> shopList2();
  List<ShopDto> shopList3();
  List<ShopDto> shopList4();
}
