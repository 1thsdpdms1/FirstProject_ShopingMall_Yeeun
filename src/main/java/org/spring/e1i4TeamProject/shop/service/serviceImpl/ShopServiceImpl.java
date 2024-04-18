package org.spring.e1i4TeamProject.shop.service.serviceImpl;

import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
@Repository
public interface ShopServiceImpl {
  void insertShop(ShopDto shopDto) throws IOException;

  void updateShopHit(Long id);

  ShopDto detail1(Long id);

  Page<ShopDto> shopSearchPagingList(Pageable pageable, String subject, String search);

  void shopUpdateOk(ShopDto shopDto) throws IOException;

  void shopDelete(Long id);
}
