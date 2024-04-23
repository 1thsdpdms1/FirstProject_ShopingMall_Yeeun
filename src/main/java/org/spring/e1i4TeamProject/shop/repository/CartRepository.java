package org.spring.e1i4TeamProject.shop.repository;

import org.spring.e1i4TeamProject.shop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
  Optional<CartEntity> findByMemberEntityId(Long id);
}
