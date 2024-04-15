package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.CartItemListEntity;
import org.spring.e1i4TeamProject.shop.entity.ItemFileEntity;
import org.spring.e1i4TeamProject.shop.entity.ItemReplyEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItemDto {
    private Long id;

    private String itemTitle;

    private String itemContent;

    private int itemPrice;

    private int count;

    private int isSoldOut;

    private int itemHit;

    private int itemAttachFile;

    private String itemSeller;

    private List<CartItemListEntity> cartItemListEntityList;

    private List<ItemFileEntity> itemFileEntityList;

    private List<ItemReplyEntity> itemReplyEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
