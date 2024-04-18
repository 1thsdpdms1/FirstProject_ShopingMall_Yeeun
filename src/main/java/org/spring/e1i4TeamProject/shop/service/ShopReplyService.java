package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.shop.dto.ShopReplyDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopReplyEntity;
import org.spring.e1i4TeamProject.shop.repository.ShopReplyRepository;
import org.spring.e1i4TeamProject.shop.repository.ShopRepository;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.ShopReplyServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopReplyService implements ShopReplyServiceImpl {

  private  final ShopRepository shopRepository;
  private final ShopReplyRepository shopReplyRepository;

  public List<ShopReplyDto> shopReplyList(Long id) {

    ShopEntity shopEntity = shopRepository.findById(id).orElseThrow(() -> {
      throw new IllegalArgumentException("아이디없음");
    });
    List<ShopReplyEntity> replyEntityList = shopReplyRepository.findAllByShopEntity(shopEntity);
    List<ShopReplyDto> shopReplyDtoList = new ArrayList<>();
    shopReplyDtoList = replyEntityList.stream().map(ShopReplyDto::toSelectShopReplyDto).collect(Collectors.toList());

    return shopReplyDtoList;
  }








}
