package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.dto.ShopDto;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopLikeRepository extends JpaRepository<ShopLikeEntity, Long> {

  Optional<ShopLikeEntity> findByShopEntityAndMemberEntity(ShopEntity shopEntity, MemberEntity memberEntity);


}
