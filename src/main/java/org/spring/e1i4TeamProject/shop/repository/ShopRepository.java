package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity,Long> {

  @Modifying
  @Query(value = " update ShopEntity b set b.shopHit=b.shopHit+1  where b.id= :id ")
  void updateShopHit(@Param("id") Long id);

  Page<ShopEntity> findByShopTitleContaining(Pageable pageable, String search);

  Page<ShopEntity> findByShopContentContaining(Pageable pageable, String search);


  List<ShopEntity> findByCategory(int i);


  List<ShopEntity> findByMemberEntityId(Long id);
//
//  Page<ShopEntity> findByShopTitleContains(Pageable pageable, String search);
//
//  Page<ShopEntity> findByShopContentContains(Pageable pageable, String search);

  Page<ShopEntity> findByCategoryAndShopTitleContains(int i, String search, Pageable pageable);

  Page<ShopEntity> findByCategoryAndShopContentContains(int i, String search, Pageable pageable);


}




