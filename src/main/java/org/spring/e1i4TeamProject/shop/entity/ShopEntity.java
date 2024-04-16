package org.spring.e1i4TeamProject.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "shop")
public class ShopEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(nullable = false)
    private String shopTitle;

    @Column(nullable = false)
    private String shopContent;

    @Column(nullable = false)
    private int shopPrice;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int isSoldOut;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int shopHit;

    @Column(nullable = false)
    private int shopAttachFile;

    @Column(nullable = false)
    private String shopSeller;

    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<CartShopListEntity> cartShopListEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<ShopFileEntity> shopFileEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<ShopReplyEntity> shopReplyEntityList;
}
