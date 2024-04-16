package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShopFileDto {
    private Long id;

    private String shopNewFileName;

    private String shopOldFileName;

    private ShopEntity shopEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
