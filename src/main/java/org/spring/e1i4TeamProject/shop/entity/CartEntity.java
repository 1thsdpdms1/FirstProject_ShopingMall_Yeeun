package org.spring.e1i4TeamProject.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.e1i4TeamProject.contraint.BaseTimeEntity;
import org.spring.e1i4TeamProject.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cart")
public class CartEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "cartEntity",
        fetch = FetchType.LAZY,
        cascade = CascadeType.REMOVE)
    private List<CartShopListEntity> cartShopListEntityList;
}
