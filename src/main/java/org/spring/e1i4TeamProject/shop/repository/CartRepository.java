package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
  Optional<CartEntity> findByMemberEntityId(Long id);

  Optional<CartEntity>  findByMemberEntity(MemberEntity memberEntity);

<<<<<<< HEAD
//  @Query(name = " select c.cart_id " +
//      "from member m inner join cart c " +
//      "on m.member_id=c.member_id " +
//      "where m.member_id=:id ", nativeQuery = true)
//  Optional<CartEntity> findMemberDB(Long id);
=======

>>>>>>> dev
}
