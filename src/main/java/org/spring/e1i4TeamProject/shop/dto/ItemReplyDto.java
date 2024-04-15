package org.spring.e1i4TeamProject.shop.dto;

import lombok.*;
import org.spring.e1i4TeamProject.shop.entity.ItemEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItemReplyDto {
    private Long id;

    private String itemReplyWriter;

    private String itemReplyContent;

    private ItemEntity itemEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
