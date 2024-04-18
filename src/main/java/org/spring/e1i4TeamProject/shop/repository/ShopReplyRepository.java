package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.spring.e1i4TeamProject.shop.entity.ShopReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopReplyRepository extends JpaRepository<ShopReplyEntity,Long> {
  List<ShopReplyEntity> findAllByShopEntity(ShopEntity shopEntity);
}
