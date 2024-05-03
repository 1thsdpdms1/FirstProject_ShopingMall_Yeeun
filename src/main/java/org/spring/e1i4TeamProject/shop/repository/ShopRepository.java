package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.board.entity.BoardEntity;
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

<<<<<<< HEAD

  List<ShopEntity> findByMemberEntityId(Long id);
//
//  Page<ShopEntity> findByShopTitleContains(Pageable pageable, String search);
//
//  Page<ShopEntity> findByShopContentContains(Pageable pageable, String search);
=======
  List<ShopEntity> findTop5ByOrderByLikedDesc();

  List<ShopEntity> findByMemberEntityId(Long id);
>>>>>>> dev

  Page<ShopEntity> findByCategoryAndShopTitleContains(int i, String search, Pageable pageable);

  Page<ShopEntity> findByCategoryAndShopContentContains(int i, String search, Pageable pageable);

<<<<<<< HEAD
=======
  //카테고리 1
  @Query(value = "select * from shop where category=1", nativeQuery = true)
  Page<ShopEntity> findByCategory1Contains(Pageable pageable);

  //카테고리가 1+ Title
  @Query(value = "  SELECT * FROM shop where category=1  and shop_title LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopTitle1Contains(Pageable pageable, @Param("search") String search);

  //카테고리가 1 + Content
  @Query(value = "  SELECT * FROM shop where category=1 and shop_content LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopContent1Contains(Pageable pageable, @Param("search") String search);

  //카테고리 2
  @Query(value = "select * from shop where category=2", nativeQuery = true)
  Page<ShopEntity> findByCategory2Contains(Pageable pageable);

  //카테고리가 2+ Title
  @Query(value = "  SELECT * FROM shop where category=2  and shop_title LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopTitle2Contains(Pageable pageable, @Param("search") String search);

  //카테고리가 2 + Content
  @Query(value = "  SELECT * FROM shop where category=2 and shop_content LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopContent2Contains(Pageable pageable, @Param("search") String search);

  //카테고리 1
  @Query(value = "select * from shop where category=3", nativeQuery = true)
  Page<ShopEntity> findByCategory3Contains(Pageable pageable);

  //카테고리가 3+ Title
  @Query(value = "  SELECT * FROM shop where category=3  and shop_title LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopTitle3Contains(Pageable pageable, @Param("search") String search);

  //카테고리가 3 + Content
  @Query(value = "  SELECT * FROM shop where category=3 and shop_content LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopContent3Contains(Pageable pageable, @Param("search") String search);

  //카테고리 4
  @Query(value = "select * from shop where category=4", nativeQuery = true)
  Page<ShopEntity> findByCategory4Contains(Pageable pageable);

  //카테고리가 4+ Title
  @Query(value = "  SELECT * FROM shop where category=4  and shop_title LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopTitle4Contains(Pageable pageable, @Param("search") String search);

  //카테고리가 4 + Content
  @Query(value = "  SELECT * FROM shop where category=4 and shop_content LIKE %:search%"
      , nativeQuery = true)
  Page<ShopEntity> findByShopContent4Contains(Pageable pageable, @Param("search") String search);
>>>>>>> dev

}




