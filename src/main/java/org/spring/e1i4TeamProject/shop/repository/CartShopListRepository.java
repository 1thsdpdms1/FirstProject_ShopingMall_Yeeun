package org.spring.e1i4TeamProject.shop.repository;

<<<<<<< HEAD
=======
import org.spring.e1i4TeamProject.member.entity.MemberEntity;
>>>>>>> dev
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.spring.e1i4TeamProject.shop.entity.CartShopListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface CartShopListRepository extends JpaRepository<CartShopListEntity,Long> {
  List<CartShopListEntity> findByCartEntityIdAndShopEntityId(Long id, Long shopId);

  List<CartShopListEntity> findAllByCartEntityId(Long id);

  List<CartShopListEntity> findByCartEntity(CartEntity cartEntity);
<<<<<<< HEAD
=======

  void deleteByIdIn(List<Long> ids);

//  List<CartShopListEntity> findByMemberEntityId(Long memberId);
>>>>>>> dev
}
