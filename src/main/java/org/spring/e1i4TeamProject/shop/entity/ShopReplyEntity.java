package org.spring.e1i4TeamProject.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.shop.dto.ShopReplyDto;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "shop_reply")
public class ShopReplyEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_reply_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String shopReplyWriter;

    @Column(nullable = false, length = 500)
    private String shopReplyContent;

    @JsonIgnore // ajax시 순환참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public static ShopReplyEntity toInsertReplyEntity(ShopReplyDto shopReplyDto) {
        ShopReplyEntity shopReplyEntity=new ShopReplyEntity();

        shopReplyEntity.setShopEntity(shopReplyDto.getShopEntity());
        shopReplyEntity.setShopReplyWriter(shopReplyDto.getShopReplyWriter());
        shopReplyEntity.setShopReplyContent(shopReplyDto.getShopReplyContent());


        return shopReplyEntity;



    }


}
