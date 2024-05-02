package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartShopListRepository extends JpaRepository<CartShopListEntity,Long> {
  List<CartShopListEntity> findByCartEntityIdAndShopEntityId(Long id, Long shopId);

  List<CartShopListEntity> findAllByCartEntityId(Long id);

  List<CartShopListEntity> findByCartEntity(CartEntity cartEntity);
}
