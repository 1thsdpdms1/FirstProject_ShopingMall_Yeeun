package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.ItemEntity;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItemListDto {
    private Long id;

    private CartEntity cartEntity;

    private ItemEntity itemEntity;

    private int count;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
