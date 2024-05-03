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


  
  @Override
  public void insertReply(ShopReplyDto shopReplyDto) {

    ShopEntity shopEntity= shopRepository.findById(shopReplyDto.getShopId()).orElseThrow(()->{
      throw new IllegalArgumentException("아이디가 없습니다.");
    });
    //해당 글이 null이 아니라면
    if (shopEntity != null) {
      ShopReplyEntity shopReplyEntity = ShopReplyEntity.builder()
          .shopEntity(ShopEntity.builder()
              .id(shopReplyDto.getShopId()).build()) // 글의 아이디
          .shopReplyWriter(shopReplyDto.getShopReplyWriter())
          .shopReplyContent(shopReplyDto.getShopReplyContent())
          .build(); // 찾아서 값 넣고
      shopReplyRepository.save(shopReplyEntity);
    }
  }
  @Override
  public List<ShopReplyDto> shopReplyList(Long id) {
    ShopEntity shopEntity = shopRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당글을 찾을 수 없습니다.:" + id));
    List<ShopReplyEntity> shopReplyEntityList = shopReplyRepository.findAllByShopEntity(shopEntity);

    List<ShopReplyDto> shopReplyDtoList = shopReplyEntityList.stream()
        .map(ShopReplyDto::toSelectReplyDto)
        .collect(Collectors.toList());

    return shopReplyDtoList;
  }

  @Transactional
  @Override
  public Long shopReplyDeleteById(Long id) {

    Long shopId = shopReplyRepository.findById(id).get().getShopEntity().getId(); //댓글 id를 찾아라

    if (shopId != null){
      shopReplyRepository.deleteById(id);
    }else{
      System.out.println("댓글삭제 실패");
    }

    return shopId;
  }
}
