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
@Table(name = "item")
public class ItemEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String itemTitle;

    @Column(nullable = false)
    private String itemContent;

    @Column(nullable = false)
    private int itemPrice;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int isSoldOut;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int itemHit;

    @Column(nullable = false)
    private int itemAttachFile;

    @Column(nullable = false)
    private String itemSeller;

    @JsonIgnore
    @OneToMany(mappedBy = "itemEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<CartItemListEntity> cartItemListEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "itemEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<ItemFileEntity> itemFileEntityList;

    @JsonIgnore
    @OneToMany(mappedBy = "itemEntity"
        ,fetch = FetchType.LAZY
        ,cascade = CascadeType.REMOVE)
    private List<ItemReplyEntity> itemReplyEntityList;
}
