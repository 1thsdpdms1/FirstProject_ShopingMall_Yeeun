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

  public static ShopReplyDto toSelectShopReplyDto(ShopReplyEntity shopReplyEntity) {
    ShopReplyDto shopReplyDto=new ShopReplyDto();
    shopReplyDto.setId(shopReplyEntity.getId());
    shopReplyDto.setShopReplyWriter(shopReplyEntity.getShopReplyWriter());
    shopReplyDto.setShopReplyContent(shopReplyEntity.getShopReplyContent());
    shopReplyDto.setShopEntity(shopReplyEntity.getShopEntity());
    shopReplyDto.setCreateTime(shopReplyEntity.getCreateTime());
    shopReplyDto.setUpdateTime(shopReplyEntity.getUpdateTime());

    return shopReplyDto;
  }
}
