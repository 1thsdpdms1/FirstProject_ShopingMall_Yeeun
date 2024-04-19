package org.spring.e1i4TeamProject.member.repository;

import org.spring.e1i4TeamProject.member.entity.MemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFileRepository extends JpaRepository<MemberFileEntity, Long> {
}
