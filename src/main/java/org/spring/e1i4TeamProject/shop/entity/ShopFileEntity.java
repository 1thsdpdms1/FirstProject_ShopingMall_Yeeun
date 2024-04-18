package org.spring.e1i4TeamProject.shop.entity;

import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.shop.dto.ShopFileDto;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "shop_file")
public class ShopFileEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_file_id")
    private Long id;

    @Column(nullable = false)
    private String shopNewFileName;

    @Column(nullable = false)
    private String shopOldFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public static ShopFileEntity toInsertShopFile(ShopFileDto shopfileDto) {
        ShopFileEntity shopFileEntity=new ShopFileEntity();
        shopFileEntity.setShopNewFileName(shopfileDto.getShopNewFileName());
        shopFileEntity.setShopOldFileName(shopfileDto.getShopOldFileName());
        shopFileEntity.setShopEntity(shopfileDto.getShopEntity());

        return shopFileEntity;
    }
}
