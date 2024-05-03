package org.spring.e1i4TeamProject.shop.service.serviceImpl;


import org.spring.e1i4TeamProject.shop.dto.ShopReplyDto;

import java.util.List;

public interface ShopReplyServiceImpl {
  void insertReply(ShopReplyDto shopReplyDto);


  List<ShopReplyDto> shopReplyList(Long id);

  Long shopReplyDeleteById(Long id);
}
