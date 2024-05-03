package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ShopServiceImpl {
  void insertShop(ShopDto shopDto) throws IOException;

  void updateShopHit(Long id);

  ShopDto detail(Long id);

  Page<ShopDto> shopSearchPagingList(Pageable pageable, String subject, String search);

  void shopUpdateOk(ShopDto shopDto) throws IOException;

  void shopDelete(Long id);

  void addCart(Long id, Long shopId,ShopDto shopDto,int priceCount);



  List<ShopDto> shopList1();
  List<ShopDto> shopList2();
  List<ShopDto> shopList3();
  List<ShopDto> shopList4();


  List<ShopDto> liked();
}
