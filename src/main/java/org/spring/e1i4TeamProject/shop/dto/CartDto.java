package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartDto {
    private Long id;

    private MemberEntity memberEntity;

    private List<CartShopListEntity> cartShopListEntityList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
