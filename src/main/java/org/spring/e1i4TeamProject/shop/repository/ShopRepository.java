package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity,Long> {

  @Modifying
  @Query(value = " update ShopEntity b set b.shopHit=b.shopHit+1  where b.id= :id ")
  void updateShopHit(@Param("id") Long id);

  Page<ShopEntity> findByShopTitleContaining(Pageable pageable, String search);

  Page<ShopEntity> findByShopContentContaining(Pageable pageable, String search);

}
