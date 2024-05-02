package org.spring.e1i4TeamProject.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cart_shop_list")
public class CartShopListEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_shop_list_id")
    private Long id;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    private int count;
}
