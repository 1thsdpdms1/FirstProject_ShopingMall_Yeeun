package org.spring.e1i4TeamProject.seller.repository;

import org.spring.e1i4TeamProject.member.entity.MemberEntity;
import org.spring.e1i4TeamProject.member.role.Role;
import org.spring.e1i4TeamProject.shop.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends JpaRepository<MemberEntity,Long> {


    @Query(value = "select m from MemberEntity m where m.role=:role")
    List<MemberEntity> findByRole(@Param("role") Role role);


}
