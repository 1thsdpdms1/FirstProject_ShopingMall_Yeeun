package org.spring.e1i4TeamProject.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;

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
    private int peopleCount;

    @Column(nullable = false)
    private int priceCount;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private String place;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int shopHit;


    @Column(nullable = false)
    private int shopAttachFile;

    @Column(nullable = false)
    private String shopSeller;

    @Column(nullable = false)
    private int liked; // 추천 수

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
    @OneToMany
        (mappedBy = "shopEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<ShopLikeEntity> ShopLikeEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<ShopReplyEntity> shopReplyEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static ShopEntity toInsertShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopTitle(shopDto.getShopTitle());
        shopEntity.setShopContent(shopDto.getShopContent());
        shopEntity.setShopPrice(shopDto.getShopPrice());
        shopEntity.setPeopleCount(shopDto.getPeopleCount());
        shopEntity.setPlace(shopDto.getPlace());
        shopEntity.setLiked(0);
        shopEntity.setShopSeller(shopDto.getShopSeller());
        shopEntity.setCategory(shopDto.getCategory());
        shopEntity.setPriceCount(0);
        shopEntity.setShopHit(0);
        shopEntity.setShopAttachFile(0);
        shopEntity.setMemberEntity(shopDto.getMemberEntity());

        return shopEntity;
    }


    public static ShopEntity toInsertFileShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopTitle(shopDto.getShopTitle());
        shopEntity.setShopContent(shopDto.getShopContent());
        shopEntity.setShopPrice(shopDto.getShopPrice());
        shopEntity.setPeopleCount(shopDto.getPeopleCount());
        shopEntity.setPlace(shopDto.getPlace());
        shopEntity.setLiked(0);
        shopEntity.setPriceCount(0);
        shopEntity.setShopSeller(shopDto.getShopSeller());
        shopEntity.setCategory(shopDto.getCategory());
        shopEntity.setShopHit(0);
        shopEntity.setShopAttachFile(1);
        shopEntity.setMemberEntity(shopDto.getMemberEntity());

        return shopEntity;
    }

    public static ShopEntity toUpdateShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopTitle(shopDto.getShopTitle());
        shopEntity.setShopContent(shopDto.getShopContent());
        shopEntity.setShopPrice(shopDto.getShopPrice());
        shopEntity.setPeopleCount(shopDto.getPeopleCount());
        shopEntity.setPlace(shopDto.getPlace());
        shopEntity.setLiked(0);
        shopEntity.setCategory(shopDto.getCategory());
        shopEntity.setShopSeller(shopDto.getShopSeller());
        shopEntity.setShopAttachFile(0);
        shopEntity.setMemberEntity(shopDto.getMemberEntity());

        return shopEntity;
    }
    public static ShopEntity toUpdateFileShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopTitle(shopDto.getShopTitle());
        shopEntity.setShopContent(shopDto.getShopContent());
        shopEntity.setShopPrice(shopDto.getShopPrice());
        shopEntity.setPeopleCount(shopDto.getPeopleCount());
        shopEntity.setPlace(shopDto.getPlace());
        shopEntity.setLiked(0);
        shopEntity.setCategory(shopDto.getCategory());
        shopEntity.setShopSeller(shopDto.getShopSeller());
        shopEntity.setShopAttachFile(1);
        shopEntity.setMemberEntity(shopDto.getMemberEntity());

        return shopEntity;
    }

    public void increaseLikeCount() {
        this.liked += 1;
    }

    public void decreaseLikeCount() {
        this.liked -= 1;
    }





}
