package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.board.dto.BoardDto;
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

  Page<ShopDto> shopList(Pageable pageable, String subject1, String subject2, String search);

  Page<ShopDto> shopSearchPageList1(Pageable pageable, String subject, String search);

  Page<ShopDto> shopSearchPageList2(Pageable pageable, String subject, String search);

  Page<ShopDto> shopSearchPageList3(Pageable pageable, String subject, String search);
  Page<ShopDto> shopSearchPageList4(Pageable pageable, String subject, String search);


  List<ShopDto> liked();
}
