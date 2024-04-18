package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.ShopFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShopFileRepository extends JpaRepository<ShopFileEntity,Long> {
  Optional<ShopFileEntity> findByShopEntityId(Long id);
}
