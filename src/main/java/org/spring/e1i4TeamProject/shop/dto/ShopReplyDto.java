package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopReplyEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShopReplyDto {
  private Long id;

  private String shopReplyWriter;

  private String shopReplyContent;

  private ShopEntity shopEntity;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private Long shopId;

  public static ShopReplyDto toSelectReplyDto(ShopReplyEntity shopReplyEntity) {
    ShopReplyDto shopReplyDto=new ShopReplyDto();
    shopReplyDto.setId(shopReplyEntity.getId());
    shopReplyDto.setShopReplyContent(shopReplyEntity.getShopReplyContent());
    shopReplyDto.setShopEntity(shopReplyEntity.getShopEntity());
    shopReplyDto.setShopReplyWriter(shopReplyEntity.getShopReplyWriter());
    shopReplyDto.setCreateTime(shopReplyEntity.getCreateTime());
    shopReplyDto.setUpdateTime(shopReplyEntity.getUpdateTime());

    return shopReplyDto;
  }
}
