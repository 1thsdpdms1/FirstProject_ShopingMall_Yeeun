package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;

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
}
