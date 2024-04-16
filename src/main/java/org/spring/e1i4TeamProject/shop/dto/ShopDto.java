package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopFileEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopReplyEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShopDto {
    private Long id;

    private String shopTitle;

    private String shopContent;

    private int shopPrice;

    private int count;

    private int isSoldOut;

    private int shopHit;

    private int shopAttachFile;

    private String shopSeller;

    private List<CartShopListEntity> cartShopListEntityList;

    private List<ShopFileEntity> shopFileEntityList;

    private List<ShopReplyEntity> shopReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
